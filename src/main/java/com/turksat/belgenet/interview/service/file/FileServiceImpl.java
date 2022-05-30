package com.turksat.belgenet.interview.service.file;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.turksat.belgenet.interview.dtos.FileDTO;
import com.turksat.belgenet.interview.models.ApplicationUser;
import com.turksat.belgenet.interview.models.File;
import com.turksat.belgenet.interview.service.user.UserDetailsServiceImpl;

@Service
public class FileServiceImpl implements FileService {
	
	private FileRepository fileRepository;
    private UserDetailsServiceImpl userDetailsService;
    
	@Autowired
	public FileServiceImpl(FileRepository fileRepository,UserDetailsServiceImpl userDetailsService) {
		super();
		this.fileRepository = fileRepository;
		this.userDetailsService=userDetailsService;
	}

	@Override
	public File saveFile(MultipartFile file,String password) throws IOException {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		 String username="";
		if (principal instanceof UserDetails) {
		  username = ((UserDetails)principal).getUsername();
		} else {
		  username = principal.toString();
		}
		ApplicationUser applicationUser = userDetailsService.findByUserName(username);

		File savedFile = new File();
		savedFile.setFileName(StringUtils.cleanPath(file.getOriginalFilename()));
		savedFile.setData(file.getBytes());
		savedFile.setUploaderKey(username);
		savedFile.setReferenceKey(UUID.randomUUID());
		savedFile.setFileLink(applicationUser.getApplicationLink()+"/"+savedFile.getReferenceKey());
		savedFile.setPassword(password);//TODO encrypt-decrpt yap
		return fileRepository.save(savedFile);
	}

	@Override
	public Optional<File> getFile(String fileLink) {
		Optional<File> file = fileRepository.findByFileLink(fileLink);
		return file;
	}

	@Override
	public List<File> getAllFiles() {
		List<File> files = fileRepository.findAll();

		return files;
	}

}
