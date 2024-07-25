package ru.gb.timesheet;

public class REST {
    /**
     * HTTP - протокол
     * gRPC - протокол
     * WebSockets - протокол
     *
     * путях/эндпоинт/ручка/ресурс - одно и тоже /timesheet
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
     */

}
