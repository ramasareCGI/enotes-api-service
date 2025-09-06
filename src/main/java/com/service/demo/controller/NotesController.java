package com.service.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.service.demo.dto.NotesDto;
import com.service.demo.service.NotesService;
import com.service.demo.util.CommonUtil;

@RestController
@RequestMapping("/api/v2/notes")
public class NotesController {

	@Autowired
	private NotesService notesService;

	@PostMapping("/saveNotes")
	public ResponseEntity<?> noteSave(@RequestParam String notes,@RequestParam(required = false) MultipartFile file) throws Exception {
		Boolean saveNotes = notesService.saveNotes(notes,file);
		if (saveNotes) {

			return CommonUtil.createBuildResponseMessage("success", HttpStatus.CREATED);
			// return new ResponseEntity<>(saveNotes,HttpStatus.CREATED);
		} else {
			return CommonUtil.createErrorMessageResponse("not saved ", HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

	@GetMapping("/getAllNotes")
	public ResponseEntity<?> getAllNotes() {
		List<NotesDto> allNotes = notesService.getAllNotes();
		if (CollectionUtils.isEmpty(allNotes)) {

			return ResponseEntity.noContent().build();
		} else {
			return CommonUtil.createBuildResponse(allNotes, HttpStatus.OK);
			// return new ResponseEntity<>(allCategory, HttpStatus.OK);
		}
	}

}
