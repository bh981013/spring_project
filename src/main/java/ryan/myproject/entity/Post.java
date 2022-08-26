package ryan.myproject.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Post {
    Long postId;
    Long memberId;
    String postTitle;
    String postBody;
    Date postDate;
}
