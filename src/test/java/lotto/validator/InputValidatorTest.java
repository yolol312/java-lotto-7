package lotto.validator;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import lotto.constant.ErrorMessage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class InputValidatorTest {
    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" "})
    void 입력_시_공백이_입력되면_예외가_발생한다(String input) {
        //given
        final String expectedMessage = ErrorMessage.INVALID_INPUT_BLANK_ERROR;

        //when & then
        assertThatThrownBy(() -> InputValidator.validateBlank(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(expectedMessage);
    }

    @ParameterizedTest
    @ValueSource(strings = {"14 000", " 7", "1, 2, 3, 4, 5, 6"})
    void 입력_시_공백이_함께_입력되면_예외가_발생한다(String input) {
        //given
        final String expectedMessage = ErrorMessage.INVALID_INPUT_WITH_BLANK_ERROR;

        //when & then
        assertThatThrownBy(() -> InputValidator.validateWhitespace(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(expectedMessage);
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", ",", "Ahn"})
    void 숫자_입력_시_문자가_입력되면_예외가_발생한다(String input) {
        //given
        final String expectedMessage = ErrorMessage.INVALID_INPUT_NUMBER_CHARACTER_ERROR;

        //when & then
        assertThatThrownBy(() -> InputValidator.validateLetter(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(expectedMessage);
    }
}