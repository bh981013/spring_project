package ryan.myproject.repository;

import ryan.myproject.entity.Post;

import java.util.List;

public interface PostRepository {

    List<Post> getPosts(Long memberId);

    void modifyPost(long postId, String postTitle, String postBody);

    void addPost(Long memberId, String postTitle, String postBody);

    Post getPost(long postId);

    void deletePost(long postId);

}
