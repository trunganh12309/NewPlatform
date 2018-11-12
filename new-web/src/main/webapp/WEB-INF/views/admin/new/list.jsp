<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="formURL" value="/admin/new/list"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Danh sách bài viết</title>
</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="breadcrumbs" id="breadcrumbs">
            <script type="text/javascript">
                try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
            </script>

            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="<c:url value="/admin/home"/>">Trang chủ</a>
                </li>
                <li class="active">Danh sách bài viết</li>
            </ul><!-- /.breadcrumb -->
        </div>
        <div class="page-content">
            <div class="row">
                <div class="col-xs-12">
                    <c:if test="${not empty messageResponse}">
                        <div class="alert alert-block alert-${alert}">
                            <button type="button" class="close" data-dismiss="alert">
                                <i class="ace-icon fa fa-times"></i>
                            </button>
                                ${messageResponse}
                        </div>
                    </c:if>
                </div>
            </div>
            <div class="row">
                <div class="col-xs-12">
                    <!-- PAGE CONTENT BEGINS -->
                    <form:form action="${formURL}" modelAttribute="model" id="formSubmit" method="get">
                    <div class="row">
                        <div class="col-xs-12">
                            <div class="widget-box table-filter">
                                <div class="widget-header">
                                    <h4 class="widget-title">Tìm kiếm</h4>
                                    <div class="widget-toolbar">
                                        <a href="#" data-action="collapse">
                                            <i class="ace-icon fa fa-chevron-up"></i>
                                        </a>
                                    </div>
                                </div>
                                <div class="widget-body">
                                    <div class="widget-main">
                                        <div class="form-horizontal">
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">Tiêu đề:</label>
                                                <div class="col-sm-8">
                                                    <div class="fg-line">
                                                        <form:input path="title" cssClass="form-control input-sm"/>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">Nội dung:</label>
                                                <div class="col-sm-8">
                                                    <div class="fg-line">
                                                        <form:input path="content" cssClass="form-control input-sm"/>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label">Thể loại:</label>
                                                <div class="col-sm-8">
                                                    <div class="fg-line">
                                                        <form:input path="categoryName" cssClass="form-control input-sm"/>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-2 control-label"></label>
                                                <div class="col-sm-8">
                                                    <button type="button" class="btn btn-sm btn-success" id="btnSearch">
                                                        Tìm kiếm
                                                        <i class="ace-icon fa fa-arrow-right icon-on-right bigger-110"></i>
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="table-btn-controls">
                                <div class="pull-right tableTools-container">
                                    <div class="dt-buttons btn-overlap btn-group">
                                        <a flag="info" class="dt-button buttons-colvis btn btn-white btn-primary btn-bold"
                                           data-toggle="tooltip" title="Thêm bài viết mới" href='<c:url value="/admin/new/edit"/>'>
                                                    <span>
                                                    <i class="fa fa-plus-circle bigger-110 purple"></i>
                                                </span>
                                        </a>
                                        <button id="btnDelete" type="button" class="dt-button buttons-html5 btn btn-white btn-primary btn-bold" disabled
                                                data-toggle="tooltip" title="Xóa bài viết">
                                                    <span>
                                                        <i class="fa fa-trash-o bigger-110 pink"></i>
                                                	</span>
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                        <div class="row">
                            <div class="col-xs-12">
                                <div class="table-responsive">
                                    <table class="table">
                                        <thead>
                                        <tr>
                                            <th><input type="checkbox" value="" id="checkAll"/></th>
                                            <th>Tên bài viết</th>
                                            <th>Thao tác</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="item" items="${model.listResult}">
                                            <tr>
                                                <td><input type="checkbox" value="${item.id}" id="checkbox_${item.id}"/></td>
                                                <td>${item.title}</td>
                                                <td>
                                                    <c:url var="editURL" value="/admin/new/edit">
                                                        <c:param name="id" value="${item.id}"/>
                                                    </c:url>
                                                    <a class="btn btn-sm btn-primary btn-edit" data-toggle="tooltip"
                                                       title="Cập nhật bài viết" href='${editURL}'><i class="fa fa-pencil-square-o" aria-hidden="true"></i>
                                                    </a>

                                                </td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                    <ul id="pagination-demo" class="pagination">
                                    </ul>
                                    <input type="hidden" name="page" id="page" value="${model.page}"/>
                                </div>
                            </div>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    var totalPages = ${model.totalPages};
    var visiblePages = ${model.maxPageItems};
    var startPage = ${model.page};
    $(document).ready(function () {
        $('#btnSearch').click(function () {
            $('#formSubmit').submit();
        });
    });
    $(function () {
        var obj = $('#pagination-demo').twbsPagination({
            totalPages: totalPages,
            visiblePages: visiblePages,
            startPage: startPage,
            // totalPages: 5,
            // visiblePages: 2,
            // startPage: 1,
            onPageClick: function (event, page) {
                if (page != startPage) {
                    $('#page').val(page);
                    $('#formSubmit').submit();
                }
            }
        });
    });
</script>
</body>
</html>
