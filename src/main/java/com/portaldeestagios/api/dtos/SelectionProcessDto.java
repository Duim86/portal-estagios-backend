package com.portaldeestagios.api.dtos;

import com.portaldeestagios.api.dtos.inputDto.StudentInput;
import com.portaldeestagios.api.selectionprocess.SelectionProcessStatusEnum;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class SelectionProcessDto implements Serializable {
  private Long id;
  private String title;

  private SelectionProcessStatusEnum status;

  private List<StudentInput> studentList;
}
