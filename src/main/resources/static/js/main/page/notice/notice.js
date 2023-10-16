const Menu = {
    menus: [],
    subMenus: [],
    load: function () {
        const that = this;
        AjaxUtil.request({
            url: '/api/main/setting/menus',
            async: false,
            success: function (data) {
                const items = data.result.items;
                that.menus = items.filter(item => item.type === "group");
                that.subMenus = items.filter(item => item.type !== "group")

                that.draw();
            },
        });
    },
    draw: function () {
        const that = this;
        const container = $('#navbarSupportedContent .navbar-nav');
        container.html('');

        this.menus.forEach(menu => {
            const menuGroup = that.createMenuGroup(menu);
            container.append(menuGroup);

            const subMenuContainer = menuGroup.find('.sub-menu');
            const subMenuItems = that.subMenus.filter(subMenu => subMenu.menu.recKey === menu.recKey);

            subMenuItems.forEach(subMenu => {
                subMenuContainer.append(that.createSubMenuItem(subMenu));
            });
        });
    },
    createMenuGroup: function (menu) {
        const that = this;
        const subMenus = this.subMenus
        let path = subMenus.url || '/';
        let activated = location.pathname ===  subMenus.url ? 'active' : '';
        if (menu.code === 'dashboard' && location.pathname === '/') {
            path = '/';
            activated = 'active';
        }

        return $(`<li class="nav-item">
                    <a class="nav-link ${activated}" href="javascript:void(0)"
                        data-bs-toggle="collapse" data-bs-target="#submenu-${menu.recKey}-1"
                        aria-controls="navbarSupportedContent" aria-expanded="false"
                        aria-label="Toggle navigation"> ${menu.name}
                    </a>
                    <ul class="sub-menu collapse" id="submenu-${menu.recKey}-1">
                    </ul>
                </li>`);
    },
    createSubMenuItem: function (subMenu) {
        const path = subMenu.url || '/';
        return $(`<li class="nav-item"><a href="${path}">${subMenu.name}</a></li>`);
    },
};

$(function () {
    const Content = {
        load: function () {
            Menu.load();
            this.event();
        },
        event: function () {
            const $body = $('body');
            const navbar = $('.navbar');

            navbar.find('.navbar-close').on({
                click: function (e) {
                    $body.removeClass('g-sidenav-pinned');
                }
            });
            navbar.find('.navbar-open').on({
                click: function (e) {
                    $body.addClass('g-sidenav-pinned');
                }
            });

            navbar.find('.sidenav-button').on({
                click: function (e) {
                    const icon = $(this).find('i');
                    icon.toggleClass('fa-compress');
                    icon.toggleClass('fa-thumbtack');
                    $body.toggleClass('g-sidenav-hidden');
                }
            })
        }
    }

    Content.load();
});