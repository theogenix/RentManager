<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<%@include file="/WEB-INF/views/common/head.jsp"%>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <!-- Left side column. contains the logo and sidebar -->
    <%@ include file="/WEB-INF/views/common/sidebar.jsp" %>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                Reservation
            </h1>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-md-12">
                    <!-- Horizontal Form -->
                    <div class="box">
                        <!-- form start -->
                        <form class="form-horizontal" method="post">

                            <div class="box-body">
                                <div class="form-group">
                                    <label for="client_id" class="col-sm-2 control-label">client_id</label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" id="client_id" name="client_id" placeholder="client_id">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="vehicle_id" class="col-sm-2 control-label">vehicle_id</label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" id="vehicle_id" name="vehicle_id" placeholder="vehicle_id">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="Start" class="col-sm-2 control-label">start</label>

                                    <div class="col-sm-10">
                                        <input type="start" class="form-control" id="start" name="start" placeholder="start">
                                    </div>
                                </div>
                                <div class="form-group">
                                      <label for="end" class="col-sm-2 control-label">end</label>
                                     <div class="col-sm-10">
                                         <input type="end" class="form-control" id="end" name="end" placeholder="end">
                                     </div>
                                </div>
                            </div>
                            <!-- /.box-body -->
                            <div class="box-footer">
                                <button type="submit" class="btn btn-info pull-right">Modifier</button>
                            </div>
                            <!-- /.box-footer -->
                        </form>
                    </div>
                    <!-- /.box -->
                </div>
                <!-- /.col -->
            </div>
        </section>
        <!-- /.content -->
    </div>

    <%@ include file="/WEB-INF/views/common/footer.jsp" %>
</div>
<!-- ./wrapper -->

<%@ include file="/WEB-INF/views/common/js_imports.jsp" %>
</body>
</html>
