package com.johnson.clusteredDataWarehouse.payload.response;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
//@Builder
public class GenericResponse<T> {
  private T data;
  private List<?> error = new ArrayList<>();

}
