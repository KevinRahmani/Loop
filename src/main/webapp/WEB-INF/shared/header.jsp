<!-- sidebar -->
<div class="container_head">
    <div class="sidebar close">
        <div class="logo-details">
            <i class='bx bx-menu' ></i>
            <span class="logo_name">Loop</span>
        </div>
        <ul class="nav-links">
            <li>
                <a href="redirection-servlet">
                    <i class='bx bx-grid-alt' ></i>
                    <span class="link_name">Accueil</span>
                </a>
                <ul class="sub-menu blank">
                    <li>
                        <a class="link_name" href="redirection-servlet">Accueil</a>
                    </li>
                </ul>
            </li>
            <li>
                <div class="icon-link">
                    <a href="redirection-servlet?requestedPage=categorie&categorie=ht">
                        <i class="fa-solid fa-laptop"></i>
                        <span class="link_name">High-Tech</span>
                    </a>
                </div>
                <ul class="sub-menu">
                    <li><a class="link_name" href="redirection-servlet?requestedPage=categorie&categorie=ht">High-Tech</a></li>
                </ul>
            </li>
            <li>
                <div class="icon-link">
                    <a href="redirection-servlet?requestedPage=categorie&categorie=bja">
                        <i class="fa-solid fa-screwdriver-wrench"></i>
                        <span class="link_name">Bricolage & co</span>
                    </a>
                </div>
                <ul class="sub-menu">
                    <li><a class="link_name" href="redirection-servlet?requestedPage=categorie&categorie=bja">Bricolage & co</a></li>
                </ul>
            </li>
            <li>
                <a href="redirection-servlet?requestedPage=categorie&categorie=mdb">
                    <i class="fa-solid fa-compact-disc"></i>
                    <span class="link_name">Musiques, DVD et Blu-ray</span>
                </a>
                <ul class="sub-menu blank">
                    <li><a class="link_name" href="redirection-servlet?requestedPage=categorie&categorie=mdb">Musiques, DVD et Blu-ray</a></li>
                </ul>
            </li>
            <li>
                <a href="redirection-servlet?requestedPage=categorie&categorie=livre">
                    <i class="fa-solid fa-book"></i>
                    <span class="link_name">Livre</span>
                </a>
                <ul class="sub-menu blank">
                    <li><a class="link_name" href="redirection-servlet?requestedPage=categorie&categorie=livre">Livre</a></li>
                </ul>
            </li>
            <li>
                <div class="icon-link">
                    <a href="redirection-servlet?requestedPage=categorie&categorie=cm">
                        <i class="fa-solid fa-chair"></i>
                        <span class="link_name">Cuisine et Maison</span>
                    </a>
                </div>
                <ul class="sub-menu">
                    <li><a class="link_name" href="redirection-servlet?requestedPage=categorie&categorie=cm">Cuisine et Maison</a></li>
                </ul>
            </li>
            <li>
                <a href="redirection-servlet?requestedPage=categorie&categorie=sports">
                    <i class="fa-solid fa-volleyball"></i>
                    <span class="link_name">Sports et Loisirs</span>
                </a>
                <ul class="sub-menu blank">
                    <li><a class="link_name" href="redirection-servlet?requestedPage=categorie&categorie=sports">Sports et Loisirs</a></li>
                </ul>
            </li>
            <li>
                <a href="redirection-servlet?requestedPage=categorie&categorie=automobile">
                    <i class="fa-solid fa-car-side"></i>
                    <span class="link_name">Automobile</span>
                </a>
                <ul class="sub-menu blank">
                    <li><a class="link_name" href="redirection-servlet?requestedPage=categorie&categorie=automobile">Automobile</a></li>
                </ul>
            </li>
            <li>
                <a href="redirection-servlet?requestedPage=categorie&categorie=vetements">
                    <i class="fa-solid fa-shirt"></i>
                    <span class="link_name">Vetements</span>
                </a>
                <ul class="sub-menu blank">
                    <li><a class="link_name" href="redirection-servlet?requestedPage=categorie&categorie=vetements">Vetements</a></li>
                </ul>
            </li>
            <li>
                <div class="profile-details">
                    <div class="name-job">
                        <div class="profile_name">

                            <c:choose>
                                <c:when test="${not empty sessionScope.user}">
                                    ${sessionScope.user.nom}
                                </c:when>
                                <c:otherwise>
                                    Non connecté
                                </c:otherwise>
                            </c:choose>
                        </div>
                        <div class="job">

                            <c:choose>
                                <c:when test="${not empty sessionScope.user}">
                                    
                                    <c:if test="${sessionScope.type eq 'client'}">
                                        Client Loop
                                    </c:if>
                                    <c:if test="${sessionScope.type eq 'vendeur'}">
                                        Partenaire Loop
                                    </c:if>
                                    <c:if test="${sessionScope.type eq 'admin'}">
                                        Admin Loop
                                    </c:if>
                                </c:when>
                                <c:otherwise>
                                    Non connecté
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                    <c:if test="${not empty sessionScope.user}">
                        <a href="disconnectController-servlet"><i id="logout" class='bx bx-log-out'></i></a>
                    </c:if>
                </div>
            </li>
        </ul>
    </div>
    <div class="container_ruban">
        <nav>
            <div id="nameLogo" class="visible">
                <svg width="175" height="45" viewBox="0 0 258 55" xmlns="http://www.w3.org/2000/svg">
                    <path d="M45.5511 3.816L26.6151 54H19.0551L0.119141 3.816H7.10314L22.8711 47.088L38.6391 3.816H45.5511Z" stroke="white" stroke-width="2.25"></path>
                    <path d="M88.7354 32.76C88.7354 34.008 88.6634 35.328 88.5194 36.72H56.9834C57.2234 40.608 58.5434 43.656 60.9434 45.864C63.3914 48.024 66.3434 49.104 69.7994 49.104C72.6314 49.104 74.9834 48.456 76.8554 47.16C78.7754 45.816 80.1194 44.04 80.8874 41.832H87.9434C86.8874 45.624 84.7754 48.72 81.6074 51.12C78.4394 53.472 74.5034 54.648 69.7994 54.648C66.0554 54.648 62.6954 53.808 59.7194 52.128C56.7914 50.448 54.4874 48.072 52.8074 45C51.1274 41.88 50.2874 38.28 50.2874 34.2C50.2874 30.12 51.1034 26.544 52.7354 23.472C54.3674 20.4 56.6474 18.048 59.5754 16.416C62.5514 14.736 65.9594 13.896 69.7994 13.896C73.5434 13.896 76.8554 14.712 79.7354 16.344C82.6154 17.976 84.8234 20.232 86.3594 23.112C87.9434 25.944 88.7354 29.16 88.7354 32.76ZM81.9674 31.392C81.9674 28.896 81.4154 26.76 80.3114 24.984C79.2074 23.16 77.6954 21.792 75.7754 20.88C73.9034 19.92 71.8154 19.44 69.5114 19.44C66.1994 19.44 63.3674 20.496 61.0154 22.608C58.7114 24.72 57.3914 27.648 57.0554 31.392H81.9674Z" stroke="white" stroke-width="2.25"></path>
                    <path d="M116.608 13.824C121.408 13.824 125.296 15.288 128.272 18.216C131.248 21.096 132.736 25.272 132.736 30.744V54H126.256V31.68C126.256 27.744 125.272 24.744 123.304 22.68C121.336 20.568 118.648 19.512 115.24 19.512C111.784 19.512 109.024 20.592 106.96 22.752C104.944 24.912 103.936 28.056 103.936 32.184V54H97.3838V14.544H103.936V20.16C105.232 18.144 106.984 16.584 109.192 15.48C111.448 14.376 113.92 13.824 116.608 13.824Z" stroke="white" stroke-width="2.25"></path>
                    <path d="M140.991 34.128C140.991 30.096 141.807 26.568 143.439 23.544C145.071 20.472 147.303 18.096 150.135 16.416C153.015 14.736 156.231 13.896 159.783 13.896C162.855 13.896 165.711 14.616 168.351 16.056C170.991 17.448 173.007 19.296 174.399 21.6V0.720001H181.023V54H174.399V46.584C173.103 48.936 171.183 50.88 168.639 52.416C166.095 53.904 163.119 54.648 159.711 54.648C156.207 54.648 153.015 53.784 150.135 52.056C147.303 50.328 145.071 47.904 143.439 44.784C141.807 41.664 140.991 38.112 140.991 34.128ZM174.399 34.2C174.399 31.224 173.799 28.632 172.599 26.424C171.399 24.216 169.767 22.536 167.703 21.384C165.687 20.184 163.455 19.584 161.007 19.584C158.559 19.584 156.327 20.16 154.311 21.312C152.295 22.464 150.687 24.144 149.487 26.352C148.287 28.56 147.687 31.152 147.687 34.128C147.687 37.152 148.287 39.792 149.487 42.048C150.687 44.256 152.295 45.96 154.311 47.16C156.327 48.312 158.559 48.888 161.007 48.888C163.455 48.888 165.687 48.312 167.703 47.16C169.767 45.96 171.399 44.256 172.599 42.048C173.799 39.792 174.399 37.176 174.399 34.2Z" stroke="white" stroke-width="2.25"></path>
                    <path d="M209.303 54.648C205.607 54.648 202.247 53.808 199.223 52.128C196.247 50.448 193.895 48.072 192.167 45C190.487 41.88 189.647 38.28 189.647 34.2C189.647 30.168 190.511 26.616 192.239 23.544C194.015 20.424 196.415 18.048 199.439 16.416C202.463 14.736 205.847 13.896 209.591 13.896C213.335 13.896 216.719 14.736 219.743 16.416C222.767 18.048 225.143 20.4 226.871 23.472C228.647 26.544 229.535 30.12 229.535 34.2C229.535 38.28 228.623 41.88 226.799 45C225.023 48.072 222.599 50.448 219.527 52.128C216.455 53.808 213.047 54.648 209.303 54.648ZM209.303 48.888C211.655 48.888 213.863 48.336 215.927 47.232C217.991 46.128 219.647 44.472 220.895 42.264C222.191 40.056 222.839 37.368 222.839 34.2C222.839 31.032 222.215 28.344 220.967 26.136C219.719 23.928 218.087 22.296 216.071 21.24C214.055 20.136 211.871 19.584 209.519 19.584C207.119 19.584 204.911 20.136 202.895 21.24C200.927 22.296 199.343 23.928 198.143 26.136C196.943 28.344 196.343 31.032 196.343 34.2C196.343 37.416 196.919 40.128 198.071 42.336C199.271 44.544 200.855 46.2 202.823 47.304C204.791 48.36 206.951 48.888 209.303 48.888Z" stroke="white" stroke-width="2.25"></path>
                    <path d="M244.701 20.952C245.853 18.696 247.485 16.944 249.597 15.696C251.757 14.448 254.373 13.824 257.445 13.824V20.592H255.717C248.373 20.592 244.701 24.576 244.701 32.544V54H238.149V14.544H244.701V20.952Z" stroke="white" stroke-width="2.25"></path>
                </svg>


            </div>
            <div class="box">
                <input id="search_produit" type="text" placeholder="Rechercher sur Loop">
                <a id="search_loupe" href="#">
                    <i class="fas fa-search"></i>
                </a>
            </div>
            <ol>
                <li><a href="redirection-servlet">Accueil</a></li>
                <li><a href="contactController-servlet">Contact</a></li>
                <li><a href="popularProduct-servlet">Best-sellers</a></li>

                <li>
                    <c:choose>

                        <c:when test="${not empty sessionScope.user}">
                            <a href="updateProfile-servlet">Compte Loop</a>
                        </c:when>
                        <c:otherwise>
                            <a href="connectController-servlet">Connexion</a>
                        </c:otherwise>
                    </c:choose>
                </li>
                <li><a href="redirection-servlet?requestedPage=basket">Panier</a></li>

            </ol>
        </nav>
        <div class="custom-shape-divider-bottom-1622817714">
            <svg data-name="Layer 1" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1200 120" preserveAspectRatio="none">
                <path d="M0,0V46.29c47.79,22.2,103.59,32.17,158,28,70.36-5.37,136.33-33.31,206.8-37.5C438.64,32.43,512.34,53.67,583,72.05c69.27,18,138.3,24.88,209.4,13.08,36.15-6,69.85-17.84,104.45-29.34C989.49,25,1113-14.29,1200,52.47V0Z" opacity=".25" class="shape-fill"></path>
                <path d="M0,0V15.81C13,36.92,27.64,56.86,47.69,72.05,99.41,111.27,165,111,224.58,91.58c31.15-10.15,60.09-26.07,89.67-39.8,40.92-19,84.73-46,130.83-49.67,36.26-2.85,70.9,9.42,98.6,31.56,31.77,25.39,62.32,62,103.63,73,40.44,10.79,81.35-6.69,119.13-24.28s75.16-39,116.92-43.05c59.73-5.85,113.28,22.88,168.9,38.84,30.2,8.66,59,6.17,87.09-7.5,22.43-10.89,48-26.93,60.65-49.24V0Z" opacity=".5" class="shape-fill"></path>
                <path d="M0,0V5.63C149.93,59,314.09,71.32,475.83,42.57c43-7.64,84.23-20.12,127.61-26.46,59-8.63,112.48,12.24,165.56,35.4C827.93,77.22,886,95.24,951.2,90c86.53-7,172.46-45.71,248.8-84.81V0Z" class="shape-fill"></path>
            </svg>
        </div>
    </div>
</div>