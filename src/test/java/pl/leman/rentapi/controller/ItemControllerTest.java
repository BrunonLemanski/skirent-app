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

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ItemService itemService;



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

        String itemJson = "{\"make\":\"Ressignol\",\"model\":\"trc\",\"price\":\"40\",\"availability\":true}";

        mockMvc.perform(post("/item/add/1")
                .content(itemJson)
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

        Item item1 = createData();

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
        String qr = "QR_7a108453-4916-4ef9-9eae-aa660dxce09b";

        Item item1 = createData();

        when(itemService.findByQrCode(qr)).thenReturn(item1);

        mockMvc.perform(get("/item?qrCode=" + qr).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.make", is("Atomic")))
                .andExpect(jsonPath("$.qrCode", is(qr)))
                .andDo(print());

        //then
        verify(itemService, times(1)).findByQrCode(any(String.class));

        assertThat(item1.getQrCode()).isEqualTo(qr);
    }

    //TODO: create test for delete
    @Test
    void shouldReturnOk_whenItemWillBeDeleted(){

    }

    /**
     * Creating data for tests.
     * @return object Item.
     */
    private Item createData() {
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
        item.setQrCode("QR_7a108453-4916-4ef9-9eae-aa660dxce09b");
        item.setReservationDate(null);
        item.setCreated_at(null);
        item.setItemCategory(category);

        return item;
    }
}