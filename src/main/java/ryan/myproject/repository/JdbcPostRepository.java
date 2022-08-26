package ryan.myproject.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ryan.myproject.entity.Post;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class JdbcPostRepository implements PostRepository{
    private final JdbcTemplate template;
    private final RowMapper<Post> postRowMapper = (resultSet, rowNum) -> {
        Post post = new Post();
        post.setMemberId(resultSet.getLong("member_id"));
        post.setPostId(resultSet.getLong("post_id"));
        post.setPostTitle(resultSet.getString("post_title"));
        post.setPostBody(resultSet.getString("post_body"));
        post.setPostDate(resultSet.getTimestamp("post_date"));
        return post;
    };

    @Autowired
    public JdbcPostRepository(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }


    @Override
    public List<Post> getPosts(Long memberId) {
        String sql = "select * from post where member_id = ?";
        return template.query(sql, postRowMapper, memberId);
    }

    @Override
    public void deletePost(long postId) {
        String sql = "delete from post where post_id = ?";
        template.update(sql, postId);
    }

    @Override
    public void modifyPost(long postId, String postTitle, String postBody) {
        String sql = "update post set post_title = ?, post_body = ? where post_id = ?";
        template.update(sql, postTitle, postBody, postId);
    }

    @Override
    public void addPost(Long memberId, String postTitle, String postBody) {
        String sql = "insert into post (member_id, post_title, post_body) values (?,?,?)";
        template.update(sql, memberId, postTitle, postBody);
        return;
    }

    @Override
    public Post getPost(long postId) {
        String sql = "select * from post where post_id= ?";
        Post post = template.queryForObject(sql, postRowMapper, postId);
        return post;
    }
}
