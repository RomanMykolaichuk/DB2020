/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.knu.fit.mit31.dbis;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author 38068
 */
@Controller
public class NewTableController {

    private final NewTableRepository newTableRepository;

    @Autowired
    public NewTableController(NewTableRepository newTableRepository) {
        this.newTableRepository = newTableRepository;
    }

    @GetMapping("/")
    public String showIndex(Model model) {

        model.addAttribute("tableRows", NewTableConvertor(newTableRepository.findAll()));

        return "index";
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
        return "index";
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
        return "index";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") int id, Model model) {
        NewTable newTable = newTableRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        newTableRepository.delete(newTable);
        model.addAttribute("tableRows", NewTableConvertor(newTableRepository.findAll()));
        return "index";
    }

    private List<NewTableConvert> NewTableConvertor(List<NewTable> modelList) {

        List<NewTableConvert> result = new ArrayList<NewTableConvert>();
        for (NewTable ml : modelList) {
            result.add(new NewTableConvert(ml));

        }

        return result;

    }
}