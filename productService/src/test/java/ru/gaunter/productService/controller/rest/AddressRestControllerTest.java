package ru.gaunter.productService.controller.rest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.gaunter.productService.dto.AddressDto;
import ru.gaunter.productService.dto.dtoforcreate.AddressCreateDto;
import ru.gaunter.productService.service.AddressServiceImpl;
import ru.gaunter.productService.service.interfaces.AddressService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class AddressRestControllerTest {

    @Mock
    AddressService addressService;

    @InjectMocks
    AddressRestController addressRestController;

    @Test
    void getByUuid() {
        UUID uuid = UUID.randomUUID();
        AddressDto addressDto = AddressDto
                .builder()
                .uuid(uuid)
                .city("asd")
                .house("asd")
                .street("asd")
                .build();

        when(addressService.getByUuid(uuid)).thenReturn(addressDto);

        assertEquals(addressDto, addressRestController.getByUuid(uuid));
    }

    @Test
    void getAll() {
        List<AddressDto> list = new ArrayList<>();
        UUID uuid = UUID.randomUUID();
        list.add(AddressDto
                .builder()
                .uuid(uuid)
                .city("asd")
                .house("asd")
                .street("asd")
                .build());

        when(addressService.getAll()).thenReturn(list);

        assertEquals(list, addressRestController.getAll());

    }

    @Test
    void create() {


        UUID uuid = UUID.randomUUID();
        AddressCreateDto addressDto = AddressCreateDto.builder()
                .uuid(uuid)
                .city("asd")
                .house("asd")
                .street("asd")
                .build();


        when(addressService.getByUuid(addressDto.getUuid())).thenReturn(new AddressDto());


        assertEquals(addressDto , addressRestController.create(addressDto));
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}