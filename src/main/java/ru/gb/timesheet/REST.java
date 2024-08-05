package ru.gb.timesheet;

public class REST {
    /**
     * HTTP - протокол
     * gRPC - протокол
     * WebSockets - протокол
     *
     * путь/эндпоинт/ручка/ресурс - одно и тоже /timesheet
     *
     * REST - набор соглашений, как оформлять/проектировать ресурсы сервиса
     * 1. Не использовать глаголы в путях
     * //deleteTimeSheet - плохо
     * DELETE /timesheet/{id} - хорошо
     *
     * 2. Ресурсы должны вкладываться друг в друга
     * GET /user/{userId}/roles/{roleId} - получить РОЛЬ с идентификатором roleId у юзера userId
     * GET /departments/{id}/employees/{id}/head/consultant/roles{id} - запрос роли консультанта consultant начальника head у подчиненного employess, находящегося в отделе department
     * GET /roles{id}
     *
     * Получить юзеров, у которых имя содержит какую-то подстроку
     * GET /user?name-like="asldkjn" -> 204 No Content
     * GET /users?sort-by=age&sort-order=desc
     *
     * 3. Рекомендация: использовать множественные числа для ресурсов
     * Лучше вместо /user использовать /users
     *
     * 4. Слова внутри ресурса соединяются дефисом
     * GET github.com/project/pull-request/{id}
     *
     *
     *
     *
     *
     */

    /**
     * API - спецификация, протокол, интерфейс, правила, контракт...
     * API - Application Programming Interface - набор правил, соглашений и т.д. Договоренность формата общения.
     *
     * Допустим, мы проектируем магазин:
     * Shop
     * -- предусмотреть шаблон для создания продукта
     * GET /products - все продукты
     * GET /products/{id} - получить конкретный продукт
     * -- Предусмотреть запрос для поиска (aka like)
     * POST /products - создать продукт
     * PUT /products - обновить продукт
     * PATH /products - обновить продукт (с выборочными полями)
     *
     * Profile - настройки пользователя
     * GET /profile/{id} - получить настройки пользователя
     * PUT /profile/{id} - обновить настройки пользователя
     * -- Для получения данных текущего пользователя лучше передавать некий "токен" в заголовке
     *
     * Refistrarion - регистрация ногово пользователя
     * POST /registration body = {}
     *
     * Cart - корзина пользователя
     * GET /cart - получить "корзину" текущего пользователя
     * POST /cart/{productId} - добавление товара в корзину
     * POST /cart/{productId}?newCount=X - сделать количество товара в корзине равным X
     * DELETE /cart/{productId} - удалить товар из корзины (с любым количеством)
     *
     *
     */

}
