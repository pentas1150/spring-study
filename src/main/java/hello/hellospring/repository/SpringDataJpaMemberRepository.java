package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    //findBy***라고 메소드 만들면 select m from Member m where m.***=?라고 알아서 만듬.
    @Override
    Optional<Member> findByName(String name);
}
