package com.softserveinc.ita;

import com.softserveinc.ita.pageobjects.*;
import com.softserveinc.ita.utils.TestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import org.testng.annotations.Test;

import static com.softserveinc.ita.pageobjects.models.SortOptionInTabQuestion.DATE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.iterator;

public class SortByDateTest extends TestRunner {

    @Description("Add test script to cover sorting function in tab list 'Питання' by date in Rozetka")
    @Issue("https://jira.softserve.academy/browse/LVTAQC672-8")
    @Test(description = "LVTAQC672-8")
    public void verifySortingFunctionByDate() {
        ProductQuestionPage productQuestionPage = homePage
                .getHeader()
                .search("iphone")
                .getProduct(1)
                .openDetailsPage()
                .openProductQuestionPage("Питання")
                .sortSelectOption(DATE);

        assertThat(productQuestionPage.getAmountQuestion())
                .as("should be greater than four")
                .isGreaterThan(4);

        for (int i = 1; i <= 4; i++){
            assertThat((productQuestionPage.getUniqueDate().get(i))
                    .after(productQuestionPage.getUniqueDate().get(i+1)))
                    .as("date should be sorted to fall down")
                    .isTrue();



//            productQuestionPage.getUniqueDate()
//                    .stream()
//                    .forEach(dateTest -> assertThat
//                            (dateTest.after(productQuestionPage.getUniqueDate().get(i+1))));
//

//            assertThat(productQuestionPage.getUniqueDate())
//                    .allSatisfy(dateQuestion ->
//                            assertThat(dateQuestion.after())
//                                    .as("date should be sorted to fall down")
//                                    .isTrue());



        }
    }
}
