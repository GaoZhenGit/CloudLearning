package com.codetend.service.txseata.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FullStep {
    public StepOneEntity stepOne;
    public StepTwoEntity stepTwo;
}
