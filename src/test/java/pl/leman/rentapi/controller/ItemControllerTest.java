package pl.leman.rentapi.controller;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pl.leman.rentapi.model.Item;
import pl.leman.rentapi.model.ItemsCategory;
import pl.leman.rentapi.service.ItemService;

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
public class ItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ItemService itemService;

    private static final String TEST_QR_CODE = "QR_7a108453-4916-4ef9-9eae-aa660dxce09b";
    private static final String TEST_ITEM_JSON = "{\"make\":\"Ressignol\",\"model\":\"trc\",\"price\":\"40\",\"availability\":true}";


    /**
     * Setting initial values for testing.
     */
    @Before
    public void setUp() {
        /*ItemsCategory category = new ItemsCategory();
        category.setId(1L);
        category.setCategoryName("narty");

        when(itemsCategoryRepository.findItemsCategoryByCategoryName(category.getCategoryName()))
                .thenReturn(category);*/
    }

    /**
     * Testing endpoint to creating new item to Item entity.
     * POST method.
     * @throws Exception
     */
    @Test
    void shouldReturnOk_whenItemWillBeCreated() throws Exception {

        mockMvc.perform(post("/item/add/1")
                .content(TEST_ITEM_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(print());

        verify(itemService, times(1)).addNewItem(any(Item.class), any(Long.class));
    }

    /**
     * Testing endpoint to getting all items from Item entity.
     * GET method.
     * @throws Exception
     */
    @Test
    void shouldReturnItems_whenAllItemsWillBeTaken() throws Exception {

        Item item1 = createTestData();

        List<Item> allItems = Arrays.asList(item1);

        given(itemService.findAllItems()).willReturn(allItems);

        mockMvc.perform(get("/item/all").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].make", is(item1.getMake())))
                .andExpect(jsonPath("$[0].model", is(item1.getModel())))
                .andExpect(jsonPath("$[0].price", is(item1.getPrice())))
                .andExpect(jsonPath("$[0].availability", is(item1.getAvailability())))
                .andExpect(jsonPath("$[0].qrCode", is(item1.getQrCode())))
                .andExpect(jsonPath("$[0].reservationDate", is(item1.getReservationDate())))
                .andExpect(jsonPath("$[0].created_at", is(item1.getCreated_at())))
                .andExpect(jsonPath("$[0].updated_at", is(item1.getUpdated_at())))
                .andExpect(jsonPath("$[0].rent", is(item1.getRent())))
                .andDo(print());
    }

    /**
     * Testing endpoint to getting Item from entity.
     * GET method.
     * @throws Exception
     */
    @Test
    void shouldReturnItem_whenItemWillBeTakenByQrCode() throws Exception {

        Item item1 = createTestData();

        when(itemService.findByQrCode(TEST_QR_CODE)).thenReturn(item1);

        mockMvc.perform(get("/item?qrCode=" + TEST_QR_CODE).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.make", is("Atomic")))
                .andExpect(jsonPath("$.qrCode", is(TEST_QR_CODE)))
                .andDo(print());

        //then
        verify(itemService, times(1)).findByQrCode(any(String.class));

        assertThat(item1.getQrCode()).isEqualTo(TEST_QR_CODE);
    }

    /**
     * Testing endpoint to getting Item by ID from entity.
     * GET method.
     * @throws Exception
     */
    @Test
    void shouldReturnOk_whenItemWillBeTakenById() throws Exception {

        Item item = createTestData();

        when(itemService.findItemById(1)).thenReturn(Optional.ofNullable(item));

        mockMvc.perform(get("/item/get?id=1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.make", is("Atomic")))
                .andExpect(jsonPath("$.qrCode", is(TEST_QR_CODE)))
                .andDo(print());

        verify(itemService, times(1)).findItemById(1);

        assertThat(item.getQrCode()).isEqualTo(TEST_QR_CODE);
    }

    /**
     * Testing endpoint for deleting Item by QrCode from Item entity.
     * DELETE method.
     * @throws Exception
     */
    @Test
    void shouldReturnOk_whenItemWillBeDeleted() throws Exception {

        Item item1 = createTestData();

        when(itemService.findByQrCode(TEST_QR_CODE)).thenReturn(item1);

        String value = "Przedmiot Atomic TRF został usunięty.";

        mockMvc.perform(delete("/item?qrCode=" + TEST_QR_CODE))
                .andExpect(status().isOk())
                .andExpect(content().string(value))
                .andDo(print());
    }

    /**
     * Creating data for tests.
     * @return object Item.
     */
    private Item createTestData() {
        ItemsCategory category = new ItemsCategory();
        Long id = 7L;
        category.setId(id);
        category.setCategoryName("akcesoria");

        Item item = new Item();
        item.setId(1L);
        item.setMake("Atomic");
        item.setModel("TRF");
        item.setPrice("50");
        item.setAvailability(true);
        item.setQrCode(TEST_QR_CODE);
        item.setReservationDate(null);
        item.setCreated_at(null);
        item.setItemCategory(category);

        return item;
    }
}