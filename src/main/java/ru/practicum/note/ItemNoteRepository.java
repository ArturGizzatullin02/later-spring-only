package ru.practicum.note;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemNoteRepository extends JpaRepository<ItemNote, Long> {

    List<ItemNote> findAllByItemUserIdAndItemUrlContaining(Long itemUserId, String itemUrl);

    @Query("select note from ItemNote as note join note.item as item where item.user.id = ?1 and ?2 member of item.tags")
    List<ItemNote> findAllByUserIdAndTags(Long userId, String tags);

    Page<ItemNote> findAllByItemUserId(Long userId, Pageable page);
}
