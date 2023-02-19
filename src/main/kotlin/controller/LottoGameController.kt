package controller

import domain.game.LottoGame
import domain.game.LottoMachine
import domain.lotto.PurchasedLotto
import domain.lotto.WinningLotto
import domain.lotto.number.LottoNumber
import util.validator.InputValidator
import view.InputView
import view.ResultView

class LottoGameController {
    private val inputView = InputView(InputValidator())
    private val resultView = ResultView()
    private val lottoGame = LottoGame(LottoMachine())

    fun startLottoGame() {
        val purchasedMoney = inputView.inputPurchasingMoney()
        val purchasedLottos = lottoGame.purchaseLottos(purchasedMoney)
        resultView.printPurchasedLottos(purchasedLottos)
        matchLottos(purchasedLottos, purchasedMoney)
    }

    private fun matchLottos(purchasedLottos: List<PurchasedLotto>, purchasedMoney: Int) {
        val winningNumbers = inputView.inputLastWeekWinningNumbers()
        val bonusNumber = inputView.inputBonusBallNumber()
        val winningLotto = WinningLotto(winningNumbers.map { LottoNumber(it) }, LottoNumber(bonusNumber))
        val matchResult = lottoGame.matchLottos(purchasedLottos, winningLotto, LottoNumber(bonusNumber))
        resultView.printWinningRate(matchResult, lottoGame.calculateIncomeRate(matchResult, purchasedMoney))
    }
}
