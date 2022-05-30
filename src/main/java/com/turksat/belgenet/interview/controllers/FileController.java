package com.turksat.belgenet.interview.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.turksat.belgenet.interview.dtos.FileDTO;
import com.turksat.belgenet.interview.models.File;
import com.turksat.belgenet.interview.service.file.FileService;

@RestController
@Transactional
@RequestMapping(value = "/files")
@CrossOrigin
public class FileController {

	private FileService fileService;

	@Autowired
	public FileController(FileService fileService) {
		super();
		this.fileService = fileService;
	}

	@PostMapping("/upload")
	public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile multipartFile,@RequestParam("password") String password) {
		try {
			fileService.saveFile(multipartFile,password);
			return ResponseEntity.status(HttpStatus.OK).body("Dosya yüklendi: " + multipartFile.getOriginalFilename());
		} catch (IOException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Dosya yüklenemedi: " + multipartFile.getOriginalFilename());
		}
	}

	@GetMapping("/list")
	public List<FileDTO> list() {
		return fileService.getAllFiles().stream().map(this::convertFileToFileDTO).collect(Collectors.toList());
	}

	private FileDTO convertFileToFileDTO(File file) {
		//TODO uploader key'e göre filtrele ve alanları dto ya maple
		FileDTO fileResponse = new FileDTO();
		/*fileResponse.setId(file.getId() + "");
		fileResponse.setName(file.getFileName());
		fileResponse.setUrl(file.getFileLink());*/
		return fileResponse;
	}

	@PostMapping("/get")//TODO DOWNLOAD
	public ResponseEntity<byte[]> getFile(@RequestParam("fileLink") String fileLink,@RequestParam("password") String password) {
		Optional<File> fileEntityOptional = fileService.getFile(fileLink);

		if (!fileEntityOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		File fileEntity = fileEntityOptional.get();
		if(fileEntity.getPassword().equals(password)) {
		return ResponseEntity.ok().body(fileEntity.getData());
		}
		return null;//TODO THROW
	}

}
