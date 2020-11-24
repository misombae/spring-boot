package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import javax.swing.text.html.Option;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {

    /*
        테스트를 먼저 개발해 놓고 MemoryMemberRepository(구현클래스)를 개발하는것을 테스트 주도 개발 TDD 라고 한다.
     */
    MemoryMemberRepository repository = new MemoryMemberRepository();

    /*
        테스트는 각각 독립적으로 실행되어야 한다. 테스트 순서에 의존관계가 있으면 안된다.
        따라서 테스트가 하나 끝나면 데이터를 clear해줘야 한다. @AfterEach : 각 테스트가 종료될때마다 이 기능을 실행한다.
     */
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save(){
        //given
        Member member = new Member();
        member.setName("spring");

        //when
        repository.save(member);

        //then
        Member result = repository.findById(member.getId()).get();

        //Assertions.assertEquals(member, result);
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring");
        repository.save(member2);

        //Optional<Member> result =  repository.findByName("spring1");
        Member result =  repository.findByName("spring1").get();
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }
}


