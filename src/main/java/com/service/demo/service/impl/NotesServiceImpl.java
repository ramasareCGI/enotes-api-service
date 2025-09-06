package com.service.demo.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import com.service.demo.util.Validation;

import org.apache.commons.io.FilenameUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.demo.dto.CategoryDto;
import com.service.demo.dto.NotesDto;
import com.service.demo.entity.FileDetails;
import com.service.demo.entity.Notes;
import com.service.demo.exception.ResourceNotFoundException;
import com.service.demo.repository.CategoryRepository;
import com.service.demo.repository.FileDetatilsRepository;
import com.service.demo.repository.NotesRepository;
import com.service.demo.service.NotesService;

@Service
public class NotesServiceImpl implements NotesService {

	@Autowired
	private Validation validation;

	@Autowired
	private NotesRepository notesRepository;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private CategoryRepository categoryRepository;

	@Value("${file.upload.path}")
	private String uploadpath;

	@Autowired
	private FileDetatilsRepository fileRepo;

	
	@Override
	public Boolean saveNotes(String notes, MultipartFile file) throws Exception {

		ObjectMapper ob = new ObjectMapper();
		NotesDto notesDto = ob.readValue(notes, NotesDto.class);

		checkCategoryExit(notesDto.getCategorydto());

		validation.notesValidation(notesDto);

		Notes notesMap = mapper.map(notesDto, Notes.class);

		FileDetails fileDetails = saveFiledetails(file);

		if (!ObjectUtils.isEmpty(fileDetails)) {
			notesMap.setFileDetails(fileDetails);
		}
		else
		{
			notesMap.setFileDetails(null);
		}

		Notes notesSave = notesRepository.save(notesMap);
		if (!ObjectUtils.isEmpty(notesSave)) {
			return true;
		}
		return false;
	}

	private FileDetails saveFiledetails(MultipartFile file) throws IOException {
		if (!ObjectUtils.isEmpty(file) && !file.isEmpty()) {
			FileDetails fileDetails = new FileDetails();
			String originalFilename = file.getOriginalFilename();

			fileDetails.setOriginalFileName(originalFilename);
			fileDetails.setDisplayFilename(getDisplayFilename(originalFilename));

			String rndString = UUID.randomUUID().toString();
			String extension = FilenameUtils.getExtension(originalFilename);
			String uploadfileName = rndString + "." + extension;
			
			fileDetails.setUploadFileName(uploadfileName);
			fileDetails.setFileSize(file.getSize());

			File saveFile = new File(uploadpath);
			if (!saveFile.exists()) {
				saveFile.mkdir();
			}
			// path :enotessapiservice/note/java.pdf

			String storePath = uploadpath.concat(uploadfileName);
			fileDetails.setPath(storePath);

			// upload file
			long upload = Files.copy(file.getInputStream(), Paths.get(storePath));
			if (upload != 0) {
				FileDetails saveFileDetails = fileRepo.save(fileDetails);
				return saveFileDetails;
			}
		}
		return null;
	}

//java _progrraming_tutorial.pdf
	private String getDisplayFilename(String originalFilename) {
		String extension = FilenameUtils.getExtension(originalFilename);
		String fileName = FilenameUtils.removeExtension(originalFilename);

		if (fileName.length() > 8) {
			fileName = fileName.substring(0, 7);
		}
		fileName = fileName + "." + extension;
		return fileName;
	}

	@Override
	public List<NotesDto> getAllNotes() {
		return notesRepository.findAll().stream().map(note -> mapper.map(note, NotesDto.class)).toList();

	}

	private void checkCategoryExit(CategoryDto categorydto) throws Exception {
		categoryRepository.findById(categorydto.getId())
				.orElseThrow(() -> new ResourceNotFoundException("category id in  invalid"));

	}
}
