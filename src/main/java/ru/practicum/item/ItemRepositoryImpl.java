package ru.practicum.item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class ItemRepositoryImpl implements ItemRepository {
    private final Map<Long, Item> items;
    private final Map<Long, List<Item>> userItems;

    @Override
    public List<Item> findByUserId(long userId) {
        return userItems.get(userId);
    }

    @Override
    public Item save(Item item) {
        item.setId(getId());
        userItems.get(item.getUserId()).add(item);
        return items.put(item.getId(), item);
    }

    @Override
    public void deleteByUserIdAndItemId(long userId, long itemId) {
        items.remove(itemId);
        userItems.remove(userId);
    }

    private long getId() {
        long lastId = items.values().stream()
                .mapToLong(Item::getId)
                .max()
                .orElse(0);
        return ++lastId;
    }
}
