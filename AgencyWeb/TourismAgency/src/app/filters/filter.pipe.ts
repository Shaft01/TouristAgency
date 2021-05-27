import { Pipe, PipeTransform } from "@angular/core";


@Pipe({
    name: 'nameFilter'
})
export class NameFilterPipe implements PipeTransform{
    transform(objects:any[],term:string):any[]{
       
        if(!objects || !term){
            return objects;
        }
        // return objects.filter(obj=>{
        //     console.log(obj.name.toLowerCase())
        //     console.log(term.toLowerCase())
        //     obj.name.toLowerCase().indexOf(term.toLowerCase()) !== -1
        // });
        return objects.filter(obj =>
            obj.name.toLowerCase().indexOf(term.toLowerCase()) !== -1);
    }
}