package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class News {

    private int id;
    private NewsCategory newsCategory;
    private String title;
    private String description;
    private String text;
    private String picUrl;
    private int userId;
    private User user;

}
