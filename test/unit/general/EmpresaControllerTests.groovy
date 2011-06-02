package general



import org.junit.*
import grails.test.mixin.*


@TestFor(EmpresaController)
@Mock(Empresa)
class EmpresaControllerTests {


    @Test
    void testIndex() {
        controller.index()
        assert "/empresa/list" == response.redirectedUrl
    }

    @Test
    void testList() {

        def model = controller.list()

        assert model.empresaInstanceList.size() == 0
        assert model.empresaInstanceTotal == 0

    }

    @Test
    void testCreate() {
       def model = controller.create()

       assert model.empresaInstance != null


    }

    @Test
    void testSave() {
        controller.save()

        assert model.empresaInstance != null
        assert view == '/empresa/create'

        // TODO: Populate valid properties

        controller.save()

        assert response.redirectedUrl == '/empresa/show/1'
        assert controller.flash.message != null
        assert Empresa.count() == 1
    }


    @Test
    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/empresa/list'


        def empresa = new Empresa()

        // TODO: populate domain properties

        assert empresa.save() != null

        params.id = empresa.id

        def model = controller.show()

        assert model.empresaInstance == empresa
    }

    @Test
    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/empresa/list'


        def empresa = new Empresa()

        // TODO: populate valid domain properties

        assert empresa.save() != null

        params.id = empresa.id

        def model = controller.edit()

        assert model.empresaInstance == empresa
    }

    @Test
    void testUpdate() {

        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/empresa/list'

        response.reset()


        def empresa = new Empresa()

        // TODO: populate valid domain properties

        assert empresa.save() != null

        // test invalid parameters in update
        params.id = empresa.id

        controller.update()

        assert view == "/empresa/edit"
        assert model.empresaInstance != null

        empresa.clearErrors()

        // TODO: populate valid domain form parameter
        controller.update()

        assert response.redirectedUrl == "/empresa/show/$empresa.id"
        assert flash.message != null
    }

    @Test
    void testDelete() {
        controller.delete()

        assert flash.message != null
        assert response.redirectedUrl == '/empresa/list'

        response.reset()

        def empresa = new Empresa()

        // TODO: populate valid domain properties
        assert empresa.save() != null
        assert Empresa.count() == 1

        params.id = empresa.id

        controller.delete()

        assert Empresa.count() == 0
        assert Empresa.get(empresa.id) == null
        assert response.redirectedUrl == '/empresa/list'


    }


}