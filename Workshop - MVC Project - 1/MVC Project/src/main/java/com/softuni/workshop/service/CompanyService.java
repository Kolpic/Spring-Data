package com.softuni.workshop.service;

import com.softuni.workshop.models.Company;

import com.softuni.workshop.models.dtos.company.ImportCompaniesDto;
import com.softuni.workshop.repositories.CompanyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyService {

    private final ModelMapper modelMapper;
    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyService(ModelMapper modelMapper, CompanyRepository companyRepository) {
        this.modelMapper = modelMapper;
        this.companyRepository = companyRepository;
    }

    public String getXMLContent() throws IOException {
        Path path = Path.of("src","main", "resources", "files", "xmls", "companies.xml");
        List<String> lines = Files.readAllLines(path);

        return String.join("\n", lines);
    }

    public void importCompanies() throws IOException, JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(ImportCompaniesDto.class);
        String xmlContent = getXMLContent();
        ByteArrayInputStream stream = new ByteArrayInputStream(xmlContent.getBytes());


        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        ImportCompaniesDto companies = (ImportCompaniesDto) unmarshaller.unmarshal(stream);

        List<Company> entities = companies.getCompanies()
                .stream()
                .map(dto -> modelMapper.map(dto, Company.class))
                .collect(Collectors.toList());

        companyRepository.saveAll(entities);
    }

    public boolean areImported() {
        return companyRepository.count() > 0;
    }
}
