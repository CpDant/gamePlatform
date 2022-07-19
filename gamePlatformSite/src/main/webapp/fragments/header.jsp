<div style="background-color: rgba(0, 0, 0, 0.05);" class='mb-3'>
	<%
		String ruoloHeader = (String) session.getAttribute("roles");
	%>
	<header class="d-flex flex-wrap justify-content-center py-3 mb-3" >


		<ul class="nav nav-pills">
			<li class="nav-item"><a href="index.jsp" class=""> <img src="img/icon/logo.png" style="width: 20%"></a></li>
			
			<%
				if(ruoloHeader == null){
			%>
			<li class="nav-item mt-1"><a href="index.jsp" class="nav-link" aria-current="page">Home</a></li>
			<li class="nav-item mt-1"><a href="catalogoVid.jsp" class="nav-link">Catalogo Videogioco</a></li>
			<li class="nav-item mt-1"><a href="catalogoAbb.jsp" class="nav-link">Catalogo Abbonamento</a></li>
			<li class="nav-item"><a href="carrello.jsp" class="nav-link btn"><img src="img\icon\shopping-cart.png" alt="add-to-cart" class="icona"></a></li>
			<li class="nav-item"><a href="login-form.jsp" class="nav-link btn"><img src="img\icon\user.png" alt="user" style="font-size:0;width:40px;height:40px;"> Accedi</a></li>
			<%
				} else if (ruoloHeader.equals("cliente")) {
			%>
			
			<li class="nav-item mt-1"><a href="index.jsp" class="nav-link" aria-current="page">Home</a></li>
			<li class="nav-item mt-1"><a href="catalogoVid.jsp" class="nav-link">Catalogo Videogioco</a></li>
			<li class="nav-item mt-1"><a href="catalogoAbb.jsp" class="nav-link">Catalogo Abbonamento</a></li>
			<li class="nav-item"><a href="carrello.jsp" class="nav-link btn"><img src="img\icon\shopping-cart.png" alt="add-to-cart" class="icona"></a></li>
			
			<li class="nav-item">
				<div class="dropdown">
					<button class="btn dropdown-toggle border-0" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><img src="img\icon\user.png" alt="user" style="font-size:0;width:40px;height:40px;"></button>
					<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
						<a class="dropdown-item" href="utentePage.jsp">Mio Profilo</a>
						<a class="dropdown-item" href="ordini.jsp">I miei ordini</a>
						<a class="dropdown-item" href="assistenza.jsp">Contatta assistenza</a>
						<a class="dropdown-item" href="LogoutServlet">Logout</a>
					</div>
				</div>
			</li>
			
			<%
				} else if (ruoloHeader.equals("supVid")) {
			%>
			<li class="nav-item mt-1"><a href="index.jsp" class="nav-link" aria-current="page">Home</a></li>
			<li class="nav-item mt-1"><a href="catalogoVidSup.jsp" class="nav-link">Gestione Videogioco</a></li>
			<li class="nav-item mt-1"><a href="catalogoAbbSup.jsp" class="nav-link">Gestione Abbonamento</a></li>
			<li class="nav-item mt-1"><a href="addInCat.jsp" class="nav-link">Modifica Catalogo</a></li>
			
			<li class="nav-item">
				<div class="dropdown">
					<button class="btn dropdown-toggle border-0" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><img src="img\icon\user.png" alt="user" style="font-size:0;width:40px;height:40px;"></button>
					<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
						<a class="dropdown-item" href="utentePage.jsp">Mio Profilo</a>
						<a class="dropdown-item" href="LogoutServlet">Logout</a>
					</div>
				</div>
			</li>
			<%
				} else if (ruoloHeader.equals("assCl")) {
			%>
			<li class="nav-item mt-1"><a href="index.jsp" class="nav-link" aria-current="page">Home</a></li>
			<li class="nav-item mt-1"><a href="gestTick.jsp" class="nav-link">Gestione Tickets</a></li>
				
			<li class="nav-item">
				<div class="dropdown">
					<button class="btn dropdown-toggle border-0" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><img src="img\icon\user.png" alt="user" style="font-size:0;width:40px;height:40px;"></button>
					<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
						<a class="dropdown-item" href="utentePage.jsp">Mio Profilo</a>
						<a class="dropdown-item" href="LogoutServlet">Logout</a>
					</div>
				</div>
			</li>
			<%
				} else if (ruoloHeader.equals("admin")) {
			%>
			<li class="nav-item mt-1"><a href="index.jsp" class="nav-link" aria-current="page">Home</a></li>
			<li class="nav-item mt-1"><a href="addOperatore.jsp" class="nav-link">Aggiungi Operatore</a></li>
				
			<li class="nav-item">
				<div class="dropdown">
					<button class="btn dropdown-toggle border-0" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><img src="img\icon\user.png" alt="user" style="font-size:0;width:40px;height:40px;"></button>
					<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
						<a class="dropdown-item" href="utentePage.jsp">Mio Profilo</a>
						<a class="dropdown-item" href="LogoutServlet">Logout</a>
					</div>
				</div>
			</li>
			<%
				}
			%>
		</ul>
	</header>

</div>