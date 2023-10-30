$(function () {
    const Content = {
        params: {},
        load: function (params) {
            this.params = params;
            Data.load({role: true});
            this.event();
        },
        event: function () {
            if(this.params.key !== ""){
            let urlDetail = '/api/applicant/findSelf/' + this.params.key
                console.log(urlDetail)
            Content.params.urlDetail = urlDetail;
            }
            const table = Table.load('#table');
            const tableDetail = TableDetail.load('#tableDetail');
        }
    };

    const Table = {
        table: null,
        load: function (target) {
            return this.draw(target);
        },
        draw: function (target) {
            const that = this;

            const roleHash = Data.roleHash || {};
            const table = new Tabulator(target, {
                locale: 'ko-kr',
                langs: TableUtil.setDefaults(),
                layout: 'fitColumns',
                placeholder: TableUtil.getPlaceholder('조건에 맞는 공지사항이 없습니다.'),
                pagination: false,
                paginationSize: paginationConfig.size,
                paginationSizeSelector: paginationConfig.selector,
                ajaxLoaderLoading: TableUtil.getLoaderLoading(),
                ajaxURL: Content.params.url,
                ajaxConfig: ajaxConfig,
                dataReceiveParams: {
                    "last_page": "lastPage",
                    "data": "items"
                },
                ajaxResponse: function (url, params, response) {
                    if (AjaxUtil.errorHandling(response)) {
                        return [];
                    }
                    response = response.result;
                    return response.items;
                },
                ajaxError: TableUtil.ajaxError,
                columnHeaderVertAlign: "middle",
                columns: [
                    {
                        formatter: "rowSelection",
                        titleFormatter: "rowSelection",
                        titleFormatterParams: {rowRange: "active"},
                        hozAlign: "center",
                        vertAlign: 'middle',
                        widthGrow: 0.5,
                        headerHozAlign: "center",
                        cellClick: function (e, cell) {
                            cell._cell.row.component.toggleSelect();
                        },
                        width: 30,
                        download: false,
                        headerSort: false
                    },
                    {
                        title: "NO",
                        formatter: "rownum",
                        hozAlign: "center",
                        headerHozAlign: "center",
                        width: 70,
                        download: false,
                        headerSort: false
                    },
                    {title: '제목', field: "title", tooltip: true, headerTooltip: true},
                    {title: '기간', field: "subtitle", tooltip: true, headerTooltip: true},
                    {title: '등록일시', field: "createUser", tooltip: true, headerTooltip: true},
                    {title: '지원자 현황', field: "createDate", tooltip: true, headerTooltip: true},

                ],
            });

            const events = {
                rowClick: function (e, row) {
                    window.location.href = '/admin/applicants/' + row.getData().recKey;
                },

                downloadComplete: function () {
                    Swal.close();
                },
                cellMouseEnter: function (e, cell, row) {
                    TableUtil.cellMouseEnter(e, cell, row)
                },
                ajaxError: TableUtil.ajaxError,
            };

            Object.entries(events).forEach(([event, callback]) => {
                table.on(event, callback);
            });

            this.table = table;
            return table;
        },
    };

    const TableDetail = {
        tableDetail: null,
        load: function (target) {
            return this.draw(target);
        },
        draw: function (target) {
            const that = this;

            console.log(Content.params);

            const roleHash = Data.roleHash || {};
            const tableDetail = new Tabulator(target, {
                locale: 'ko-kr',
                langs: TableUtil.setDefaults(),
                layout: 'fitColumns',
                placeholder: TableUtil.getPlaceholder('조건에 맞는 공지사항이 없습니다.'),
                pagination: false,
                paginationSize: paginationConfig.size,
                paginationSizeSelector: paginationConfig.selector,
                ajaxLoaderLoading: TableUtil.getLoaderLoading(),
                ajaxURL: Content.params.urlDetail,
                ajaxConfig: ajaxConfig,
                dataReceiveParams: {
                    "last_page": "lastPage",
                    "data": "items"
                },
                ajaxResponse: function (url, params, response) {
                    if (AjaxUtil.errorHandling(response)) {
                        return [];
                    }
                    response = response.result;
                    return response.items;
                },
                ajaxError: TableUtil.ajaxError,
                columnHeaderVertAlign: "middle",
                columns: [
                    {
                        formatter: "rowSelection",
                        titleFormatter: "rowSelection",
                        titleFormatterParams: {rowRange: "active"},
                        hozAlign: "center",
                        vertAlign: 'middle',
                        widthGrow: 0.5,
                        headerHozAlign: "center",
                        cellClick: function (e, cell) {
                            cell._cell.row.component.toggleSelect();
                        },
                        width: 30,
                        download: false,
                        headerSort: false
                    },
                    {
                        title: "NO",
                        formatter: "rownum",
                        hozAlign: "center",
                        headerHozAlign: "center",
                        width: 70,
                        download: false,
                        headerSort: false
                    },
                    {title: '이름', field: "title", tooltip: true, headerTooltip: true},
                    {title: '제목', field: "subtitle", tooltip: true, headerTooltip: true},
                    {title: '연락처', field: "createUser", tooltip: true, headerTooltip: true},

                ],
            });

            const events = {

                rowClick: function (e, row) {
                    window.location.href = '/admin/notice/detail/' + row.getData().recKey;

                },
                downloadComplete: function () {
                    Swal.close();
                },
                cellMouseEnter: function (e, cell, row) {
                    TableUtil.cellMouseEnter(e, cell, row)
                },
                ajaxError: TableUtil.ajaxError,
            };

            Object.entries(events).forEach(([event, callback]) => {
                tableDetail.on(event, callback);
            });

            this.table = tableDetail;
            return tableDetail;
        },
    };

    Content.load({
        key: $('.param[name="key"]').val() || '',
        url: "/api/job/find",
    });
})