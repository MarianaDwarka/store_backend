package com.marianadwarka.controller;

import com.marianadwarka.dto.CategoryDTO;
import com.marianadwarka.dto.GenericResponse;
import com.marianadwarka.dto.GenericResponseRecord;
import com.marianadwarka.model.Category;
import com.marianadwarka.service.ICategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/categories")
//@RequestMapping("${url.path}")
@CrossOrigin(origins = "*") //permite a cualquiera que quiera consumir el backend (Esto lo usé desde Angular)
@RequiredArgsConstructor
public class CategoryController {

    //@Autowired
    private final ICategoryService service;// = new CategoryService();
    @Qualifier("categoryMapper")
    private final ModelMapper modelMapper;

    @PreAuthorize("@authService.hasAccess()")
    //@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    //@PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public ResponseEntity<GenericResponseRecord<CategoryDTO>> readAll() throws Exception{
        List<CategoryDTO> list = service.readAll().stream().map(this::convertToDto).toList();

        return ResponseEntity.ok(new GenericResponseRecord<>(200, "success", new ArrayList<>(list)));
        //return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /*@GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> readById(@PathVariable("id") Integer id) throws Exception{
        CategoryDTO dto = convertToDto(service.readById(id));

        return ResponseEntity.ok(dto);
    }*/

    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse<CategoryDTO>> readById(@PathVariable("id") Integer id) throws Exception{
        CategoryDTO dto = convertToDto(service.readById(id));

        return ResponseEntity.ok(new GenericResponse<>(200, "success", Arrays.asList(dto)));
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> save(@Valid @RequestBody CategoryDTO dto) throws Exception{
        Category obj = service.save(convertToEntity(dto));

        return new ResponseEntity<>(convertToDto(obj), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody CategoryDTO dto) throws Exception{
        //dto.setIdCategory(id);
        Category obj = service.update(convertToEntity(dto), id);

        return ResponseEntity.ok(convertToDto(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
        service.delete(id);

        return ResponseEntity.noContent().build();
        //return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //////////////queries////////////////////
    @GetMapping("/find/name/{name}")
    public ResponseEntity<List<CategoryDTO>> findByName(@PathVariable("name") String name){
        List<CategoryDTO> list = service.findByName(name).stream().map(this::convertToDto).toList();

        return ResponseEntity.ok(list);
    }

    @GetMapping("/find/name/like/{name}")
    public ResponseEntity<List<CategoryDTO>> findByNameLike(@PathVariable("name") String name){
        List<CategoryDTO> list = service.findByNameLike(name).stream().map(this::convertToDto).toList();

        return ResponseEntity.ok(list);
    }

    @GetMapping("/find/name/enabled")
    public ResponseEntity<List<CategoryDTO>> findByNameAndEnabled(@RequestParam("name") String name, @RequestParam("enabled") boolean enabled){
        List<CategoryDTO> list = service.findByNameAndEnabled(name, enabled).stream().map(this::convertToDto).toList();

        return ResponseEntity.ok(list);
    }

    @GetMapping("/get/name/description1")
    public ResponseEntity<List<CategoryDTO>> getNameAndDescription1(@RequestParam("name") String name, @RequestParam("description") String description) throws Exception {
        List<CategoryDTO> lst = service.getNameAndDescription1(name, description).stream().map(this::convertToDto).toList();

        return ResponseEntity.ok(lst);
    }

    @GetMapping("/get/name/description2")
    public ResponseEntity<List<CategoryDTO>> getNameAndDescription2(@RequestParam("name") String name, @RequestParam("description") String description) throws Exception {
        List<CategoryDTO> lst = service.getNameAndDescription2(name, description).stream().map(this::convertToDto).toList();

        return ResponseEntity.ok(lst);
    }

    @GetMapping("/get/name/sql")
    public ResponseEntity<List<CategoryDTO>> getNameSQL(@RequestParam("name") String name) throws Exception {
        List<CategoryDTO> lst = service.getNameSQL(name).stream().map(this::convertToDto).toList();

        return ResponseEntity.ok(lst);
    }

    @GetMapping("/pagination")
    public ResponseEntity<Page<CategoryDTO>> findPage(Pageable pageable){
        Page<CategoryDTO> page = service.findPage(pageable).map(this::convertToDto);

        return ResponseEntity.ok(page);
    }

    @GetMapping("/pagination2")
    public ResponseEntity<Page<CategoryDTO>> findPage2(
            @RequestParam(name = "p", defaultValue = "0") int page,
            @RequestParam(name = "s", defaultValue = "3") int size
    ){
        Page<CategoryDTO> pageDTO = service.findPage(PageRequest.of(page, size)).map(this::convertToDto);

        return ResponseEntity.ok(pageDTO);
    }

    @GetMapping("/order")
    public ResponseEntity<List<CategoryDTO>> findAllOrder(@RequestParam(name = "param", defaultValue = "ASC") String param){
        List<CategoryDTO> list = service.findAllOrder(param).stream().map(this::convertToDto).toList();

        return ResponseEntity.ok(list);
    }


    ////////////////////////////
    private CategoryDTO convertToDto(Category obj){
        return modelMapper.map(obj, CategoryDTO.class);
    }

    private Category convertToEntity(CategoryDTO dto){
        return modelMapper.map(dto, Category.class);
    }

    /*public CategoryController(ICategoryService service) {
        this.service = service;
    }*/

    /*@GetMapping
    public Category save(){
        Category category = new Category();

        return service.saveAndValid(category);
    }*/

    /*@GetMapping
    public Category sayHello(){
        return new Category(1, "COMPUTERS");
    }*/

}
