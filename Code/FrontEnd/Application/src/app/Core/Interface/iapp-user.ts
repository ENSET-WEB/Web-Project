import { IAppRole } from './iapp-role';

export interface IAppUser {
  id: string;
  name: string;
  email: string;
  appRoles: IAppRole[];
}
