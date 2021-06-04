import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { EmployeeCreateManage } from "./employee-create-manage/employee-create-manage.component";
import { LoginComponent } from './login/login.component';

const routes: Routes = [
  { path: '', redirectTo:"login",pathMatch:"full" },
  { path: 'home', component: EmployeeCreateManage },
  { path: 'login', component: LoginComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { useHash: true, relativeLinkResolution: 'legacy' })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
