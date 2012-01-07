/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import java.rmi.RemoteException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import exceptions.*;
import entityBeans.UserBeanRemote;

/**
 *
 * @author Admin
 */
public class JSPHelper {

    private static String userMenu = " <ul><li class=\"first\"><span>Меню пользователя</span></li> <li><a href=\"/ProShop-war/basket\"><span>Корзина</span></a></li> <li><a href=\"/ProShop-war/getOrders\"><span>История покупок</span></a></li><li><a href=\"/ProShop-war/updateUser.jsp?DO=updateProfil\"><span>Редактирование профиля</span></a></li><li><a href=\"/ProShop-war/getUsersByRole.jsp\"><span>Поиск пользователей</span></a></li><li><a href=\"/ProShop-war/getFullList.jsp\"><span>Вывод всего</span></a></li><li class=\"last\"><a href=\"/ProShop-war/index.jsp\"><span>На главную</span></a></li></ul>";
    private static String adminMenu = " <ul><li class=\"first\"><span>Меню пользователя</span></li> <li><a href=\"/ProShop-war/basket\"><span>Корзина</span></a></li> <li><a href=\"/ProShop-war/getOrders\"><span>История покупок</span></a></li><li><a href=\"/ProShop-war/add_catalog.jsp\"><span>Добавление каталога</span></a></li><li><a href=\"/ProShop-war/updateCatalog.jsp?DO=select\"><span>Редактирование каталога</span></a></li><li><a href=\"/ProShop-war/del_catalog.jsp\"><span>Удаление каталога</span></a></li><li><a href =\"/ProShop-war/addProduct.jsp\"><span>Добавление продукта</span></a></li><li><a href=\"/ProShop-war/updateProduct.jsp?DO=select\"><span>Изменение продукта</span></a></li><li><a href =\"/ProShop-war/delProduct.jsp\"><span>Удаление продукта</span></a></li><li><a href=\"/ProShop-war/updateUser.jsp?DO=updateProfil\"><span>Редактирование профиля</span></a></li><li><a href=\"/ProShop-war/deleteUser.jsp\"><span>Блокировать пользователя</span></a></li><li><a href=\"/ProShop-war/updateUser.jsp?DO=updateUser\"><span1>Редактирование пользователя</span1></a></li><li><a href=\"/ProShop-war/getUsersByRole.jsp\"><span>Поиск пользователей</span></a></li><li><span>Экспорт</span><ul><li><a href=\"/ProShop-war/XML/exportProduct.jsp?poiskType=byPrice\" class=\"first\"><span1>Экспортировать продукты используя поиск по цене</span1></a></li><li><a href=\"/ProShop-war/XML/exportProduct.jsp?poiskType=byName\"><span1>Экспортировать продукты используя поиск по имени</span1></a></li><li><a href=\"/ProShop-war/XML/exportUser.jsp\"><span>Экспорт пользователей</span></a></li></ul></li> <li><a href=\"/ProShop-war/XML/import.jsp\"><span>Импорт</span></a></li> <li><a href=\"/ProShop-war/XML/history.jsp\"><span>История (вывод в XML)</span></a></li><li><a href=\"/ProShop-war/getFullList.jsp\"><span>Вывод всего</span></a></li><li class=\"last\"><a href=\"/ProShop-war/index.jsp\"><span>На главную</span></a></li></ul>";
    private static String managerMenu = " <ul><li class=\"first\"><span>Меню пользователя</span></li> <li><a href=\"/ProShop-war/basket\"><span>Корзина</span></a></li> <li><a href=\"/ProShop-war/getOrders\"><span>История покупок</span></a></li><li><a href=\"/ProShop-war/add_catalog.jsp\"><span>Добавление каталога</span></a></li><li><a href=\"/ProShop-war/updateCatalog.jsp?DO=select\"><span>Редактирование каталога</span></a></li><li><a href=\"/ProShop-war/del_catalog.jsp\"><span>Удаление каталога</span></a></li><li><a href =\"/ProShop-war/addProduct.jsp\"><span>Добавление продукта</span></a></li><li><a href=\"/ProShop-war/updateProduct.jsp?DO=select\"><span>Изменение продукта</span></a></li><li><a href =\"/ProShop-war/delProduct.jsp\"><span>Удаление продукта</span></a></li><li><a href=\"/ProShop-war/updateUser.jsp?DO=updateProfil\"><span>Редактирование профиля</span></a></li><li><a href=\"/ProShop-war/getUsersByRole.jsp\"><span>Поиск пользователей</span></a></li><li><a href=\"/ProShop-war/getFullList.jsp\"><span>Вывод всего</span></a></li><li class=\"last\"><a href=\"/ProShop-war/index.jsp\"><span>На главную</span></a></li></ul>";
    private static String guestMenu = " <ul><li class=\"first\"><span>Меню пользователя</span></li> <li><a href=\"/ProShop-war/getFullList.jsp\"><span>Вывод всего</span></a></li> <li class=\"last\"><a href=\"/ProShop-war/index.jsp\"><span>На главную</span></a></li></ul>";
    public static String getMenu(long role) {
        String menu = "";
        if (role == 1) {
            menu = adminMenu;
        } else {
            if (role == 2) {
                menu = managerMenu;
            } else {
                if (role == 3) {
                menu = userMenu;
            } else {
                menu = guestMenu;
                }
        }
        
    }
        return menu;
    }
 
    public static String getRequestOrEmpty(HttpServletRequest request, String name) {
        if (request.getAttribute(name) != null) {
            return request.getAttribute(name).toString();
        } else {
            return "";
        }
    }

    public static UserBeanRemote getUser2(HttpSession sess) throws LoginException {
        Object user = sess.getAttribute("user");

        if (user == null) {
            throw new LoginException();
        } else {
            try {
                UserBeanRemote usr = (UserBeanRemote) sess.getAttribute("user");
                if(usr.getRoleId()==4){
                    throw new LoginException("Вы не обладаете правами");
                }
                if (usr.getRoleId() == 1) {
                    return usr;
                } else {
                    return usr;
                }
            } catch (RemoteException ex) {
                throw new LoginException("Произошла ошибка повторите попытку");
            }
        }

    }
}