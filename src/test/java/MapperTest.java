import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.news.NewsApplication;
import com.news.domain.entity.Category;
import com.news.mapper.CategoryMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = NewsApplication.class)
public class MapperTest {
    @Autowired
    CategoryMapper categoryMapper;
    @Test
    public void test(){
        QueryWrapper<Category> categoryQueryWrapper = new QueryWrapper<>();
        List<Category> categories = categoryMapper.selectList(categoryQueryWrapper);
        for (Category category:categories){
            System.out.println(categories);
        }
    }
}
