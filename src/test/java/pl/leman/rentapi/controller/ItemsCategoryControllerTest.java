package pl.leman.rentapi.controller;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pl.leman.rentapi.model.ItemsCategory;
import pl.leman.rentapi.repository.ItemsCategoryRepository;
import pl.leman.rentapi.service.ItemsCategoryService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ItemsCategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ItemsCategoryService itemsCategoryService;

    @Autowired
    private ItemsCategoryRepository itemsCategoryRepository;


    /**
     * Setting initial value for testing.
     */
    @Before
    public void setUp() {
        ItemsCategory category = new ItemsCategory();
        Long id = 1L;
        category.setId(id);
        category.setCategoryName("narty");

        Mockito.when(itemsCategoryRepository.findItemsCategoryByCategoryName(category.getCategoryName()))
                .thenReturn(category);
    }

    /**
     * Testing endpoint to getting all objects from ItemsCategory entity.
     * GET method
     * @throws Exception
     */
    @Test
    void shouldReturnOk_whenAllItemCategoryWillBeTaken() throws Exception {
        ItemsCategory category = new ItemsCategory();
        Long id = 1L;
        category.setId(id);
        category.setCategoryName("gogle");

        List<ItemsCategory> allCategories = Arrays.asList(category);

        given(itemsCategoryService.findAllCategory()).willReturn(allCategories);

        mockMvc.perform(get("/category/all").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].categoryName", is(category.getCategoryName())));
    }

    /**
     * Testing endpoint to adding new category to ItemsCategory entity.
     * POST method
     * @throws Exception
     */
    @Test
    void shouldReturnOK_whenItemCategoryWillBeCreated() throws Exception {

        String categoryJson = "{\"categoryName\":\"narty\"}";

        mockMvc.perform(post("/category/add")
                .content(categoryJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(print());

        verify(itemsCategoryService, times(1)).addNewCategory(any(ItemsCategory.class));
    }

    /**
     * Testing endpoint to getting category by name from ItemsCategory entity.
     * GET method.
     */
    @Test
    void shouldReturnCategory_whenItemCategoryWillBeTaken() {

        String categoryName = "narty";

        //when
        ItemsCategory foundCategory = itemsCategoryRepository.findItemsCategoryByCategoryName(categoryName);

        //then
        assertThat(foundCategory.getCategoryName()).isEqualTo(categoryName);
    }

    /**
     * Testing endpoint to getting category by id from ItemsCategory entity.
     * GET method.
     */
    @Test
    void shouldReturnCategory_whenItemCategoryWillBeTakenById() {

        //when
        Optional<ItemsCategory> foundCategory = itemsCategoryService.findCategoryById(1);

        //then
        assertThat(foundCategory.isPresent() && "narty".equals(foundCategory.get()));
    }

    /**
     * Testing endpoint to deleting category by name From ItemsCategory entity.
     * DELETE method.
     * @throws Exception
     */
    @Test
    void shouldReturnHttpOk_whenItemCategoryWillBeDeleted() throws Exception {
        String categoryName = "narty";

        String value = "Kategoria NARTY została usunięta";

        mockMvc.perform(delete("/category/" + categoryName))
                .andExpect(status().isOk())
                .andExpect(content().string(value));
    }
}