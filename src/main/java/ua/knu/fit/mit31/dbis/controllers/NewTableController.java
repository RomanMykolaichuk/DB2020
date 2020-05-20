/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.knu.fit.mit31.dbis.controllers;

import java.io.IOException;
import ua.knu.fit.mit31.dbis.repositories.NewTableRepository;
import ua.knu.fit.mit31.dbis.repositories.ChildTableRepository;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.knu.fit.mit31.dbis.dao.NewTable;

/**
 *
 * @author 38068
 */
@Controller
public class NewTableController {

    private final NewTableRepository newTableRepository;
    private final ChildTableRepository childTableRepository;

    @Autowired
    public NewTableController(NewTableRepository newTableRepository, ChildTableRepository childTableRepository) {
        this.newTableRepository = newTableRepository;
        this.childTableRepository = childTableRepository;

    }

    @GetMapping("/")
    public String showIndex(Model model) {

        model.addAttribute("tableRows", NewTableConvertor(newTableRepository.findAll()));

        return "index";
    }
    
     @GetMapping("/child/{id}")
    public String showChildAmount(@PathVariable("id") int id, Model model) {
         
        model.addAttribute("tableRows", NewTableConvertor(newTableRepository.findByChildrenAmount(id)));
        return "redirect:/";
    }
    

    @GetMapping("/signup")
    public String showAddForm(NewTable newTable) {
        return "add-rows";
    }
    

    @PostMapping("/addrows")
    public String addUser(@Valid NewTable newTable, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-rows";
        }

        newTableRepository.save(newTable);
        model.addAttribute("tableRows", NewTableConvertor(newTableRepository.findAll()));
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        NewTable newTable = newTableRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid row Id:" + id));

        model.addAttribute("tableRows", newTable);
        return "update-rows";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") int id, @Valid NewTable newTable,
            BindingResult result, Model model) {
        if (result.hasErrors()) {
            newTable.setId(id);
            model.addAttribute("tableRows", newTable);
            return "update-rows";
        }
        newTableRepository.save(newTable);
        model.addAttribute("tableRows", NewTableConvertor(newTableRepository.findAll()));
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") int id, Model model) {
        NewTable newTable = newTableRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        newTableRepository.delete(newTable);
        model.addAttribute("tableRows", NewTableConvertor(newTableRepository.findAll()));
        return "redirect:/";
       
        
    }
    
    @GetMapping("/file")
    public String fileChoosing() {

        return "file";
    }

    @PostMapping("/upload")
    public String singleFileUpload(@RequestParam("file") MultipartFile file,
            RedirectAttributes redirectAttributes) {

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:uploadStatus";
        }

        try {

            ArrayList<NewTable> dataPopulating
                    = CSVParsing.loadData(file);
            int rowsCount = 0;
            for (NewTable newRow : dataPopulating) {

                newTableRepository.save(newRow);
                rowsCount++;
            }

            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded " + rowsCount
                    + " rows from '" + file.getOriginalFilename() + "'");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/uploadStatus";
    }

    @GetMapping("/uploadStatus")
    public String uploadStatus() {
        return "uploadStatus";
    }

    private Collection<NewTableConvert> NewTableConvertor(Collection<NewTable> modelList) {

        Collection<NewTableConvert> result = new ArrayList<>();
        for (NewTable ml : modelList) {
            result.add(new NewTableConvert(ml));
        }

        return result;

    }
}
