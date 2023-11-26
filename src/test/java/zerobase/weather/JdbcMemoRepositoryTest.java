package zerobase.weather;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import zerobase.weather.domain.Memo;
import zerobase.weather.repository.JdbcMemoRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional // 데이터베이스 테스트 할 때 많이 사용하는 어노테이션, 데이터베이스에 값이 들어가지 않게 해줌
public class JdbcMemoRepositoryTest {

    @Autowired
    JdbcMemoRepository jdbcMemoRepository;

    @Test
    void insertMemoTest() {

        //given ~~ 이 주어졌을 떄
        Memo newMemo = new Memo(2,"insertMemoTest");

        //when ~~을 했을 때
        jdbcMemoRepository.save(newMemo);

        //then 그러고 나면 이럴것이다~
        Optional<Memo> result = jdbcMemoRepository.findById(2);
        assertEquals(result.get().getText(), "insertMemoTest");
    }

    @Test
    void findAllMemoTest(){
        List<Memo> memoList = jdbcMemoRepository.findAll();
        System.out.println(memoList.get(0));
        assertNotNull(memoList);
    }
}
