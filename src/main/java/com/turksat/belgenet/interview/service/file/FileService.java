package com.turksat.belgenet.interview.service.file;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.turksat.belgenet.interview.dtos.FileDTO;
import com.turksat.belgenet.interview.models.File;

public interface FileService {
	public File saveFile(MultipartFile file,String password) throws IOException;//TODO DTO ile hem file ve detay bilgileri al

	public Optional<File> getFile(String fileLink);

	public List<File> getAllFiles();

}
