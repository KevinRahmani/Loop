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
                <svg width="180" height="55" viewBox="0 -20 327 139" xmlns="http://www.w3.org/2000/svg" id="svglogo">
                    <path d="M259.567 36.632C262.159 32.12 265.999 28.376 271.087 25.4C276.271 22.328 282.271 20.792 289.087 20.792C296.095 20.792 302.431 22.472 308.095 25.832C313.855 29.192 318.367 33.944 321.631 40.088C324.895 46.136 326.527 53.192 326.527 61.256C326.527 69.224 324.895 76.328 321.631 82.568C318.367 88.808 313.855 93.656 308.095 97.112C302.431 100.568 296.095 102.296 289.087 102.296C282.367 102.296 276.415 100.808 271.231 97.832C266.143 94.76 262.255 90.968 259.567 86.456V138.44H246.463V22.088H259.567V36.632ZM313.135 61.256C313.135 55.304 311.935 50.12 309.535 45.704C307.135 41.288 303.871 37.928 299.743 35.624C295.711 33.32 291.247 32.168 286.351 32.168C281.551 32.168 277.087 33.368 272.959 35.768C268.927 38.072 265.663 41.48 263.167 45.992C260.767 50.408 259.567 55.544 259.567 61.4C259.567 67.352 260.767 72.584 263.167 77.096C265.663 81.512 268.927 84.92 272.959 87.32C277.087 89.624 281.551 90.776 286.351 90.776C291.247 90.776 295.711 89.624 299.743 87.32C303.871 84.92 307.135 81.512 309.535 77.096C311.935 72.584 313.135 67.304 313.135 61.256Z"  stroke="white" stroke-width="2.25"></path>
                    <path d="M188.77 102.296C181.378 102.296 174.658 100.616 168.61 97.256C162.658 93.896 157.954 89.144 154.498 83C151.138 76.76 149.458 69.56 149.458 61.4C149.458 53.336 151.186 46.232 154.642 40.088C158.194 33.848 162.994 29.096 169.042 25.832C175.09 22.472 181.858 20.792 189.346 20.792C196.834 20.792 203.602 22.472 209.65 25.832C215.698 29.096 220.45 33.8 223.906 39.944C227.458 46.088 229.234 53.24 229.234 61.4C229.234 69.56 227.41 76.76 223.762 83C220.21 89.144 215.362 93.896 209.218 97.256C203.074 100.616 196.258 102.296 188.77 102.296ZM188.77 90.776C193.474 90.776 197.89 89.672 202.018 87.464C206.146 85.256 209.458 81.944 211.954 77.528C214.546 73.112 215.842 67.736 215.842 61.4C215.842 55.064 214.594 49.688 212.098 45.272C209.602 40.856 206.338 37.592 202.306 35.48C198.274 33.272 193.906 32.168 189.202 32.168C184.402 32.168 179.986 33.272 175.954 35.48C172.018 37.592 168.85 40.856 166.45 45.272C164.05 49.688 162.85 55.064 162.85 61.4C162.85 67.832 164.002 73.256 166.306 77.672C168.706 82.088 171.874 85.4 175.81 87.608C179.746 89.72 184.066 90.776 188.77 90.776Z"  stroke="white" stroke-width="2.25"></path>
                    <path d="M96.6603 102.296C89.2683 102.296 82.5483 100.616 76.5003 97.256C70.5483 93.896 65.8443 89.144 62.3883 83C59.0283 76.76 57.3483 69.56 57.3483 61.4C57.3483 53.336 59.0763 46.232 62.5323 40.088C66.0843 33.848 70.8843 29.096 76.9323 25.832C82.9803 22.472 89.7483 20.792 97.2363 20.792C104.724 20.792 111.492 22.472 117.54 25.832C123.588 29.096 128.34 33.8 131.796 39.944C135.348 46.088 137.124 53.24 137.124 61.4C137.124 69.56 135.3 76.76 131.652 83C128.1 89.144 123.252 93.896 117.108 97.256C110.964 100.616 104.148 102.296 96.6603 102.296ZM96.6603 90.776C101.364 90.776 105.78 89.672 109.908 87.464C114.036 85.256 117.348 81.944 119.844 77.528C122.436 73.112 123.732 67.736 123.732 61.4C123.732 55.064 122.484 49.688 119.988 45.272C117.492 40.856 114.228 37.592 110.196 35.48C106.164 33.272 101.796 32.168 97.0923 32.168C92.2923 32.168 87.8763 33.272 83.8443 35.48C79.9083 37.592 76.7403 40.856 74.3403 45.272C71.9403 49.688 70.7403 55.064 70.7403 61.4C70.7403 67.832 71.8923 73.256 74.1963 77.672C76.5963 82.088 79.7643 85.4 83.7003 87.608C87.6363 89.72 91.9563 90.776 96.6603 90.776Z"  stroke="white" stroke-width="2.25"></path>
                    <path d="M13.192 90.344H48.328V101H0.0880127V0.631989H13.192V90.344Z"  stroke="white" stroke-width="5"></path>
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