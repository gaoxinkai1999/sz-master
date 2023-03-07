package com.example.sz.Cv;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.awt.*;


@Component
@Data
public class Cv_Result {
    private double score;
    private Point mid;

}
