import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AgmCoreModule } from '@agm/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { RegistroComponent } from './registro/registro.component';
import { PantallaInicioComponent } from './pantalla-inicio/pantalla-inicio.component';
import { ClimaComponent } from './clima/clima.component';
import { LoginService } from './login/services/login.service';
import { RegistroService } from './registro/services/registro.service';
import { UsuarioService } from './usuario/services/usuario.service';
import { ClimaService } from './clima/services/clima.service';
import { VerUsuarioComponent } from './usuario/ver-usuario/ver-usuario.component';
import { EditarUsuarioComponent } from './usuario/editar-usuario/editar-usuario.component';
import { ArmarioComponent } from './armario/armario.component';
import { FavoritosComponent } from './favoritos/favoritos.component';
import { FavoritoService } from './favoritos/services/favorito.service';
import { ArmarioService } from './armario/services/armario.service';
import { SuperiorComponent } from './prenda/superior/superior.component';
import { InferiorComponent } from './prenda/inferior/inferior.component';
import { ZapatosComponent } from './prenda/zapatos/zapatos.component';
import { AccesoriosComponent } from './prenda/accesorios/accesorios.component';
import { PrendaService } from './prenda/services/prenda.service';
import { AgregarPrendaComponent } from './prenda/agregar-prenda/agregar-prenda.component';
import { HistorialComponent } from './historial/historial.component';
import { GenerarAtuendoComponent } from './generar-atuendo/generar-atuendo.component';
import { CompartirComponent } from './compartir/compartir.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegistroComponent,
    PantallaInicioComponent,
    ClimaComponent,
    VerUsuarioComponent,
    EditarUsuarioComponent,
    ArmarioComponent,
    FavoritosComponent,
    SuperiorComponent,
    InferiorComponent,
    ZapatosComponent,
    AccesoriosComponent,
    AgregarPrendaComponent,
    HistorialComponent,
    GenerarAtuendoComponent,
    CompartirComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyAgAhAvvWusl4vOH4FG5o4mGRaivuQXm68'
    })
  ],
  providers: [
    LoginService,
    RegistroService,
    UsuarioService,
    ClimaService,
    FavoritoService,
    ArmarioService,
    PrendaService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
