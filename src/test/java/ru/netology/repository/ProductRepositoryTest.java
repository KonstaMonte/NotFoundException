package ru.netology.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.NotFoundException;
import ru.netology.domain.Product;
import ru.netology.domain.TShirt;

import static org.junit.jupiter.api.Assertions.*;


public class ProductRepositoryTest {
    private ProductRepository repository = new ProductRepository();

    private Product item1 = new Book(1, "Тревожные люди", 200, "Фредрик Бакман");
    private Product item2 = new Book(2, "Мы начинаем в конце", 250, "Крис Уитакер");
    private Product item3 = new TShirt(3, "Синяя", 350, "Adidas");
    private Product item4 = new TShirt(4, "Черная", 1000, "Nike");

    @BeforeEach
    void setUp() {
        repository.save(item1);
        repository.save(item2);
        repository.save(item3);
        repository.save(item4);
    }

    @Test
    void shouldRemoveByExistId() {
        repository.removeById(1);
        Product[] actual = repository.findAll();
        Product[] expected = {
                item2,
                item3,
                item4
        };
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldRemoveByIdNotExist() {
        assertThrows(NotFoundException.class, () -> repository.removeById(42));
    }
}