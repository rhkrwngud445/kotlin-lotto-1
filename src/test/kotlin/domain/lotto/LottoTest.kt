package domain.lotto

import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class LottoTest {
    @ParameterizedTest
    @MethodSource("provideSixLottoNumbers")
    fun `6개의 로또 번호 리스트가 주어졌을 때, 로또 객체 생성시, 예외 발생하지 않는다`(lottoNumbers: List<LottoNumber>) {
        assertDoesNotThrow {
            Lotto(lottoNumbers)
        }
    }

    companion object {
        @JvmStatic
        fun provideSixLottoNumbers(): List<Arguments> =
            listOf(
                Arguments.of(
                    (1..6).map { number ->
                        LottoNumber(number)
                    }
                )
            )
    }
}
