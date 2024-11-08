package lab.home.tradingplatform.auth.domain

class User(
    _id: UserId = UserId()
) {
    var id: UserId = _id
        private set
}
