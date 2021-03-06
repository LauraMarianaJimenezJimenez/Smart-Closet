import { Component, OnInit } from '@angular/core';
import { Usuario } from '../services/usuario';
import { Router, ActivatedRoute } from '@angular/router';
import { UsuarioService } from '../services/usuario.service';

@Component({
  selector: 'app-editar-usuario',
  templateUrl: './editar-usuario.component.html',
  styleUrls: ['./editar-usuario.component.css']
})
export class EditarUsuarioComponent implements OnInit {

  user: Usuario = undefined;
  llegoUsuario = false;
  confirmPass = '';
  message = '';

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private userService: UsuarioService
  ) { }

  ngOnInit(): void {

    this.userService.findByNickname(localStorage.getItem('User')).subscribe(
      results => {
        this.user = results;
        this.inicializar();
      }
    );

  }

  inicializar() {
    this.llegoUsuario = true;
  }

  edit() {

    if (this.confirmPass !== '') {
      if (this.user.password === this.confirmPass) {
        this.userService.update(this.user).subscribe(
          result => {
            localStorage.setItem('User', this.user.nickname);
            this.router.navigate(['/perfil']);
            console.log(result);
          },
          error => {
            console.log(error);
          }
        );
      } else {
        this.message = 'Contraseña incorrecta';
      }
    } else {
      this.message = 'Digita tu contraseña para confirmar cambios';
    }

  }

}
