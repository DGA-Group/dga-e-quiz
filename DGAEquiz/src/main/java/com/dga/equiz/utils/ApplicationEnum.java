package com.dga.equiz.utils;

import java.util.HashMap;

public class ApplicationEnum {

    //region Question type
    public enum QuestionType {
        ImageQuestion,
        FillQuestion,
        ListeningQuestion,
        TranslateQuestion
    }
    //endregion

    //region Anchor type
    public enum AnchorType {
        FitToParent,
        Center
    }
    //endregion

    public static class Color{
        // Tỉ lệ màu : 60 30 10.
        public static final String WhiteSmoke = "#F5F8FA";      // Trắng 60%
        public static final String LightSkyBlue = "#C9E6FF";    // Xanh lam pastel 30%
        public static final String Aquamarine = "#B4DDD3";      // Xanh lá pastel 30%
        public static final String Goldenrod = "#FFC75A";       // Vàng 10%
        public static final String LightGray = "#C4C4C4";       // Xám 1%
        public static final String DarkBlue = "#5D8DBF";       // Xanh lam tối 1%
        public static final String MidnightBlue = "#444766";    // Xanh lam đêm 1%
        public static final String Black = "#000000";           // Đen 1%
        public static final String LightRed = "#FFBFBF";
    }


}
