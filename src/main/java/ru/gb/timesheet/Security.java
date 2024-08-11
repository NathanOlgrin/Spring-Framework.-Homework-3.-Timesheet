package ru.gb.timesheet;

public class Security {

    /**
     * Authority (Role) - Какая-то строка, которая описывает возможность получения какого-то ресурса.
     * admin, user, anonymous, role_create_user, role_delete_user - всё это роли
     *
     * SecurityContext - контейнер, в котором хранятся текущие привелегии "пользователя" (клиента)
     *
     * SecurityFilterChain - Набор (цепочка) "фильтров", которые срабатывают на каждый запрос на сервер и каждый из этих "фильтров" пытается понять, "то" пришёл на сервер, и можно ли ему отдавать запрашиваемый ресурс
     *
     *
     *
     * OAuth2
     * client -> AuthorizationServer
     * client <- token <- AuthorizationServer
     *
     * client - token -> ResourceServer - token> AuthorizationServer
     *
     */

}
