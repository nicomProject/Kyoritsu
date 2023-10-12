$(function () {
    const Content = {
        params: {},
        load: function (params) {
            this.params = params;

            Data.load({role: true});
            this.event();
        },
        event: function () {
            const table = Table.load('#table');
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
                placeholder: TableUtil.getPlaceholder('조건에 맞는 소개글이 없습니다.'),
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
                    {title: '카테고리', field: "category", tooltip: true, headerTooltip: true, headerFilter: 'input'},
                    {title: '서브 카테고리', field: "subcategory", tooltip: true, headerTooltip: true, headerFilter: 'input'},
                    {title: '제목', field: "title", tooltip: true, headerTooltip: true, headerFilter: 'input'},
                    {title: '소제목', field: "subtitle", tooltip: true, headerTooltip: true, headerFilter: 'input'},
                    {
                        title: '<i class="fas fa-clock"></i> 등록 일시',
                        field: "createDate",
                        tooltip: true,
                        headerTooltip: true
                    },

                    {
                        title: '작업',
                        width: 140,
                        headerHozAlign: 'center',
                        hozAlign: 'center',
                        headerSort: false,
                        tooltip: true,
                        headerTooltip: true,
                        download: false,
                        //visible: authInfo.edit,
                        formatter: function (cell, formatterParams, onRendered) {
                            const row = cell.getRow();
                            const data = row.getData();
                            const that = $(cell.getElement())
                            const value = cell.getValue() || '0';

                            const buttons = document.querySelectorAll("button");

                            // 모든 버튼에 클릭 이벤트 리스너를 추가합니다.
                            buttons.forEach(function (button) {
                                button.addEventListener("click", function () {
                                    // data-action 속성을 확인하여 해당 동작을 처리합니다.
                                    const action = button.getAttribute("data-action");
                                    if (action === "delete") {

                                        const selected = table.getSelectedData().map(e => e.recKey);
                                        const firstSelected = selected[0];
                                        console.log(selected.length + "selected")
                                        console.log(selected+ "selected222")
                                        if(selected.length == 1){
                                        AjaxUtil.requestBody({
                                            url: '/api/introductions/delete',
                                            data: {
                                                type: 'one',
                                                id: firstSelected
                                            },
                                            success: function (data) {
                                                console.log(data)
                                                if (data.code == 200) {
                                                }
                                            }
                                        })
                                        }
                                        else{
                                            AjaxUtil.requestBody({
                                                url: '/api/introductions/delete',
                                                data: {
                                                    type: 'list',
                                                    idListLong: selected
                                                },
                                                success: function (data) {
                                                    console.log(data)
                                                    if (data.code == 200) {
                                                    }
                                                }
                                            })
                                        }

                                    }
                                });
                            });

                            //return section;
                        }
                    },
                ],
            });

            const events = {

                rowClick: function (e, row) {
                    window.location.href = 'introductionsDetail?row=' + row.getData().recKey;

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

    Content.load({
        url: "/api/introductions/find"
    });
})