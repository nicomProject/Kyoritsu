const SideBar = {
    menus: [],
    load: function () {
        const that = this;
        // const dbUse = false;
        const dbUse = true;
        //const menus = [];
        //menus.push({code: '안녕'})
        console.log(dbUse)
        if(dbUse){
            AjaxUtil.request({
                url: '/api/menus',
                async: false,
                success: function (data) {
                    console.log("data ======zz", data)
                    console.log("data.result.items", data.result.items)
                    that.menus = data.result.items;
                    that.draw();
                }
            })
        }
        // else {
        //     const menus = [];
        //     menus.push({})
        // }
    },
    draw: function () {
        const that = this;
        const container = $('.navbar.sidenav .navbar-nav');
        container.html('');
        this.menus.forEach(menu => {
            container.append(that.createMenuItem(menu));
        });
    },
    createMenuItem: function(menu) {
        let path = '/admin/' + menu.code;
        let activated = location.pathname.replace("/admin/", "") === menu.code ? 'active' : '';
        if (menu.code === 'dashboard' && location.pathname === '/') {
            path = '/';
            activated = 'active';
        }

        console.log("menu.name12" + menu.name)
        return `<li class="nav-item">
                    <a class="nav-link ${activated}" href="${path}">
                        <div class="icon icon-shape icon-sm">
                            <i class="${menu.icon}"></i>
                        </div>
                        <span class="nav-link-text ms-2">${menu.name}</span>
                    </a>
                </li>`;
    }
};

$(function () {
    const Content = {
        load: function () {
            SideBar.load();

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

            tippy('*[role="action"][data-action="shield"]', {
                content: '비밀번호 변경'
            });
            tippy('*[role="action"][data-action="logout"]', {
                content: '로그아웃'
            });

            navbar.find('*[role="action"]').click(function (e) {
                const action = this.dataset.action;
                if (action === 'logout') {
                    Alert.confirm({
                        title: '로그아웃',
                        text: '로그아웃 하시겠습니까?',
                    }, function (result) {
                        if (result.isConfirmed) {
                            location.href = '/admin/logout';
                        }
                    });
                } else if (action === 'shield') {
                    ParamManager.show('password', 'password', {});
                }
            });
        }
    }

    Content.load();
});