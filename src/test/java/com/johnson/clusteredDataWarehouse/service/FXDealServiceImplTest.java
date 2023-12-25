package com.johnson.clusteredDataWarehouse.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.johnson.clusteredDataWarehouse.exception.CustomException;
import com.johnson.clusteredDataWarehouse.model.FXDeal;
import com.johnson.clusteredDataWarehouse.payload.request.FXDealRequest;
import com.johnson.clusteredDataWarehouse.payload.response.FXDealResponse;
import com.johnson.clusteredDataWarehouse.repository.FXDealRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {FXDealServiceImpl.class})
@ExtendWith(SpringExtension.class)
class FXDealServiceImplTest {
    @MockBean
    private FXDealRepository fXDealRepository;

    @Autowired
    private FXDealServiceImpl fXDealServiceImpl;

    @MockBean
    private ModelMapper modelMapper;


    @Test
    void testCreateFXDeal() {
        FXDeal fxDeal = new FXDeal();
        fxDeal.setAmount(new BigDecimal("2.3"));
        fxDeal.setId(1L);
        fxDeal.setOrderTimeStamp(LocalDate.of(1970, 1, 1).atStartOfDay());
        fxDeal.setRequestId("42");
        Optional<FXDeal> ofResult = Optional.of(fxDeal);
        when(fXDealRepository.findFXDealByRequestId(Mockito.<String>any())).thenReturn(ofResult);
        assertThrows(CustomException.class, () -> fXDealServiceImpl.createFXDeal(new FXDealRequest()));
        verify(fXDealRepository).findFXDealByRequestId(Mockito.<String>any());
    }


    @Test
    void testCreateFXDeal2() {
        FXDeal fxDeal = new FXDeal();
        fxDeal.setAmount(new BigDecimal("2.3"));
        fxDeal.setId(1L);
        fxDeal.setOrderTimeStamp(LocalDate.of(1970, 1, 1).atStartOfDay());
        fxDeal.setRequestId("42");
        when(fXDealRepository.save(Mockito.<FXDeal>any())).thenReturn(fxDeal);
        Optional<FXDeal> emptyResult = Optional.empty();
        when(fXDealRepository.findFXDealByRequestId(Mockito.<String>any())).thenReturn(emptyResult);
        FXDealResponse fxDealResponse = new FXDealResponse();
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<FXDealResponse>>any())).thenReturn(fxDealResponse);
        FXDealResponse actualCreateFXDealResult = fXDealServiceImpl.createFXDeal(new FXDealRequest());
        verify(fXDealRepository).findFXDealByRequestId(Mockito.<String>any());
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<FXDealResponse>>any());
        verify(fXDealRepository).save(Mockito.<FXDeal>any());
        assertSame(fxDealResponse, actualCreateFXDealResult);
    }

    @Test
    void testGetAllFXDeal() {
        when(fXDealRepository.findAll()).thenReturn(new ArrayList<>());
        List<FXDealResponse> actualAllFXDeal = fXDealServiceImpl.getAllFXDeal();
        verify(fXDealRepository).findAll();
        assertTrue(actualAllFXDeal.isEmpty());
    }


    @Test
    void testGetAllFXDeal2() {
        FXDeal fxDeal = new FXDeal();
        fxDeal.setAmount(new BigDecimal("2.3"));
        fxDeal.setId(1L);
        fxDeal.setOrderTimeStamp(LocalDate.of(1970, 1, 1).atStartOfDay());
        fxDeal.setRequestId("42");

        ArrayList<FXDeal> fxDealList = new ArrayList<>();
        fxDealList.add(fxDeal);
        when(fXDealRepository.findAll()).thenReturn(fxDealList);
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<FXDealResponse>>any())).thenReturn(new FXDealResponse());
        List<FXDealResponse> actualAllFXDeal = fXDealServiceImpl.getAllFXDeal();
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<FXDealResponse>>any());
        verify(fXDealRepository).findAll();
        assertEquals(1, actualAllFXDeal.size());
    }


    @Test
    void testGetUniqueDeal() {
        FXDeal fxDeal = new FXDeal();
        fxDeal.setAmount(new BigDecimal("2.3"));
        fxDeal.setId(1L);
        fxDeal.setOrderTimeStamp(LocalDate.of(1970, 1, 1).atStartOfDay());
        fxDeal.setRequestId("42");
        Optional<FXDeal> ofResult = Optional.of(fxDeal);
        when(fXDealRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        FXDealResponse fxDealResponse = new FXDealResponse();
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<FXDealResponse>>any())).thenReturn(fxDealResponse);
        FXDealResponse actualUniqueDeal = fXDealServiceImpl.getUniqueDeal(1L);
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<FXDealResponse>>any());
        verify(fXDealRepository).findById(Mockito.<Long>any());
        assertSame(fxDealResponse, actualUniqueDeal);
    }

    @Test
    void testGetUniqueDeal2() {
        FXDeal fxDeal = new FXDeal();
        fxDeal.setAmount(new BigDecimal("2.3"));
        fxDeal.setId(1L);
        fxDeal.setOrderTimeStamp(LocalDate.of(1970, 1, 1).atStartOfDay());
        fxDeal.setRequestId("42");
        Optional<FXDeal> ofResult = Optional.of(fxDeal);
        when(fXDealRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<FXDealResponse>>any()))
                .thenThrow(new CustomException("An error occurred"));
        assertThrows(CustomException.class, () -> fXDealServiceImpl.getUniqueDeal(1L));
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<FXDealResponse>>any());
        verify(fXDealRepository).findById(Mockito.<Long>any());
    }


}
