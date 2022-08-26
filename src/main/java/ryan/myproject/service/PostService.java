package ryan.myproject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ryan.myproject.entity.Member;
import ryan.myproject.entity.Post;
import ryan.myproject.repository.MemberRepository;
import ryan.myproject.repository.PostRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final MemberRepository memberRepository;
    private final PostRepository postRepository;

    public List<Post> getPostLists(long memberId) {
        List<Post> posts = postRepository.getPosts(memberId);
        return posts;
    }

    public void addPost(long memberId, String postTitle, String postBody) {
        postRepository.addPost(memberId, postTitle, postBody);
        return;
    }

    public Post getSinglePost(long postId) {
        return postRepository.getPost(postId);
    }

    public void deletePost(long postId) {
        postRepository.deletePost(postId);
        return;
    }

    public void modifyPost(long postId, String postTitle, String postBody) {
        postRepository.modifyPost(postId, postTitle, postBody);
    }
}
