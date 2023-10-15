package com.dga.equiz.model;

import com.dga.equiz.model.question.Question;

import java.util.ArrayList;
import java.util.List;

public class Lesson {
    private final long lessonNumber;
    private List<Question> questions = new ArrayList<Question>();

    public Lesson(long lessonNumber){
        this.lessonNumber = lessonNumber;
    }
}
